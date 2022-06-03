package spring3.spring3study.config.proxy.v6_aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Around("execution(* spring3.spring3study.app..*(..)) && !execution(* spring3.spring3study.app..noLog(..))") // Pointcut 역할
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        // Advice 로직
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }


}
