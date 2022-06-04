package springstudy.springaopstudy.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV6Advice {

//    @Around("springstudy.springaopstudy.order.aop.Pointcuts.orderAndService()")
//    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
//        try {
//            //Before
//            log.info("[Transaction 시작] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            //AfterReturning
//            log.info("[Transaction 커밋] {}", joinPoint.getSignature());
//            return result;
//        }catch (Exception e){
//            //AfterThrowing
//            log.info("[Transaction 롤백] {}", joinPoint.getSignature());
//            throw e;
//        }finally {
//            //After
//            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
//        }
//    }

    @Before("springstudy.springaopstudy.order.aop.Pointcuts.orderAndService()")
    public void before(JoinPoint joinPoint){
        log.info("[Before] {}", joinPoint.getSignature());

        // 이 후 알아서 target 메서드 실행됨.
    }

    @AfterReturning(value = "springstudy.springaopstudy.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        log.info("[AfterRetuning] {}, [return] {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "springstudy.springaopstudy.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        log.info("[AfterThrowing] {}, [message] {}", joinPoint.getSignature(), ex.getMessage());
    }

    @After(value = "springstudy.springaopstudy.order.aop.Pointcuts.orderAndService()")
    public void after(JoinPoint joinPoint){
        log.info("[After] {}", joinPoint.getSignature());
    }
}
