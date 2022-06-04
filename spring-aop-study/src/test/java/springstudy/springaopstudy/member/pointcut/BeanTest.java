package springstudy.springaopstudy.member.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springstudy.springaopstudy.order.OrderService;

@Slf4j
@Import({BeanTest.AtBeanAspect.class})
@SpringBootTest
public class BeanTest {

    @Autowired
    OrderService orderService;

    @Test
    void success(){
        log.info("orderService Proxy = {}", orderService.getClass());
        orderService.orderItem("helloA");
    }

    @Slf4j
    @Aspect
    static class AtBeanAspect {

        @Around("bean(*Service) || bean(orderRepository)")
        public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[bean] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
