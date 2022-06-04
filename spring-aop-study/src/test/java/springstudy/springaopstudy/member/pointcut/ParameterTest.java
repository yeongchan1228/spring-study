package springstudy.springaopstudy.member.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springstudy.springaopstudy.member.MemberService;
import springstudy.springaopstudy.member.annotation.ClassAop;
import springstudy.springaopstudy.member.annotation.MethodAop;

@Slf4j
@Import({ParameterTest.ParameterAspect.class})
@SpringBootTest
public class ParameterTest {

    @Autowired MemberService memberService;

    @Test
    void success(){
        log.info("memberService Proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }


    @Slf4j
    @Aspect
    static class ParameterAspect{

        @Pointcut("execution(* springstudy.springaopstudy.member..*.*(..))")
        private void allMember() {}

        @Around("allMember()")
        public Object execute1(ProceedingJoinPoint joinPoint) throws Throwable{
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArg1]{}, args = {}", joinPoint.getSignature(), arg1);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg, ..)")
        public Object execute2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable{
            log.info("[logArg1]{}, args = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg, ..)")
        public void execute3(String arg){
            log.info("[before] args = {}", arg);
        }

        /**
         * proxy를 받는다.
         */
        @Before("allMember() && this(obj)")
        public void thisArgs(MemberService obj){
            log.info("[this] Obj.getClass() = {}", obj.getClass());
        }

        /**
         * 실제 객체를 받는다.
         */
        @Before("allMember() && target(obj)")
        public void targetArgs(MemberService obj){
            log.info("[target] Obj.getClass() = {}", obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void atTargetArgs(ClassAop annotation){
            log.info("[@target] annotation = {}", annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void atWithinArgs(ClassAop annotation){
            log.info("[@within] annotation = {}", annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void atAnnotation(MethodAop annotation){
            log.info("[@annotation] annotation = {}", annotation.value());
        }
    }
}
