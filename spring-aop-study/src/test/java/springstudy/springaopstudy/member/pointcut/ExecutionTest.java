package springstudy.springaopstudy.member.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import springstudy.springaopstudy.member.MemberServiceImpl;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {
     AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
     Method helloMethod;

     @BeforeEach
     public void init() throws NoSuchMethodException {
         helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
     }
     
     @Test
     public void printMethod() throws Exception {
         // public java.lang.String springstudy.springaopstudy.member.MemberServiceImpl.hello(java.lang.String)
         log.info("helloMethod = {}", helloMethod);
     }
}
