package springstudy.springaopstudy.type.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class ProxyDiAspect {

    @Before("execution(* springstudy.springaopstudy..*.*(..))")
    public void execute(JoinPoint joinPoint){
        log.info("[ProxyDiAspect] {}", joinPoint.getSignature());
    }
}
