package spring3.spring3study.config.proxy.v3_proxyfactory.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

@Slf4j
public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace logTrace;

    public LogTraceAdvice(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
        status = logTrace.begin(invocation.getMethod().getDeclaringClass().getSimpleName() +
                "." + invocation.getMethod().getName());

        Object result = invocation.proceed();

        logTrace.end(status);
        return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
