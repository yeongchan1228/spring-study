package spring3.spring3study.config.proxy.v2_dynamicproxy.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // method 이름 필터
        String methodName = method.getName();
        if(!PatternMatchUtils.simpleMatch(patterns, methodName)){
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            status = logTrace.begin(method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()");

            Object result = method.invoke(target, args);

            logTrace.end(status);

            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }

    }
}
