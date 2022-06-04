package springstudy.springaopstudy.member.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springstudy.springaopstudy.member.MemberService;

@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
//@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLIB Proxy
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK Dynamic Proxy
public class ThisTargetTest {

    @Autowired MemberService memberService;

    @Test
    void success(){
        log.info("memberService Proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect{
        @Around("this(springstudy.springaopstudy.member.MemberService)")
        public Object execute1(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(springstudy.springaopstudy.member.MemberService)")
        public Object execute2(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("this(springstudy.springaopstudy.member.MemberServiceImpl)")
        public Object execute3(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-concrete] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(springstudy.springaopstudy.member.MemberServiceImpl) && args(arg, ..)")
        public Object execute4(ProceedingJoinPoint joinPoint, String arg) throws Throwable {
            log.info("[target-concrete] {}, arg = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }
    }
}
