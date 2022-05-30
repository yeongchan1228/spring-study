package spring3.spring3study.config.proxy.v2_dynamicproxy.handler;

import lombok.extern.slf4j.Slf4j;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;

    public LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
