package springstudy.springaopstudy.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {

    @Aspect
    @Order(2)
    public static class LogAspect{

        @Around("springstudy.springaopstudy.order.aop.Pointcuts.allOrder()")
        public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[log] {}", joinPoint.getSignature());

            return joinPoint.proceed();
        }

    }

    @Aspect
    @Order(1)
    public static class TransactionAspect{

        @Around("springstudy.springaopstudy.order.aop.Pointcuts.orderAndService()")
        public Object transaction(ProceedingJoinPoint joinPoint) throws Throwable{
            try {
                log.info("[Transaction 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[Transaction 커밋] {}", joinPoint.getSignature());

                return result;
            }catch (Exception e){
                log.info("[Transaction 롤백] {}", joinPoint.getSignature());
                throw e;
            }finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }

    }
}
