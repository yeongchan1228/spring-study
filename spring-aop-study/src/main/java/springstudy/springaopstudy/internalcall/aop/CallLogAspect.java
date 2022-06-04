package springstudy.springaopstudy.internalcall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class CallLogAspect {

    @Before("execution(* springstudy.springaopstudy.internalcall..*.*(..))")
    public void log(JoinPoint joinPoint){
        log.info("[log] {}", joinPoint.getSignature());
    }
}
