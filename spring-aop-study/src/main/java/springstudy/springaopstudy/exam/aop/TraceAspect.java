package springstudy.springaopstudy.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {

    @Before("@annotation(springstudy.springaopstudy.exam.annotation.Trace)")
    public void Log(JoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        log.info("[trace] {}, args = {}", joinPoint.getSignature(), args);
    }
}
