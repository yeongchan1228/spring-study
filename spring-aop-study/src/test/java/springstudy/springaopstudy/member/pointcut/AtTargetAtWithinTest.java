package springstudy.springaopstudy.member.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springstudy.springaopstudy.member.MemberServiceImpl;
import springstudy.springaopstudy.member.annotation.ClassAop;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Import({AtTargetAtWithinTest.Config.class})
@SpringBootTest
public class AtTargetAtWithinTest {

    @Autowired Child child;

    @Test
    void success(){
        log.info("child proxy = {}", child.getClass());
        child.childMethod();
        child.parentMethod();
    }

     static class Config{
        @Bean
        public Parent parent(){
            return new Parent();
        }

        @Bean
        public Child child(){
            return new Child();
        }

        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect(){
            return new AtTargetAtWithinAspect();
        }
    }

    @Slf4j
    @Aspect
    static class AtTargetAtWithinAspect{
        // 부모까지 허용
        @Around("execution(* springstudy.springaopstudy..*(..)) && @target(springstudy.springaopstudy.member.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // 해당 클래스 내 메서드만 허용
        @Around("execution(* springstudy.springaopstudy..*(..)) && @within(springstudy.springaopstudy.member.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    static class Parent{
         public void parentMethod(){}
    }

    @ClassAop
    static class Child extends Parent{
         public void childMethod(){}
    }

}
