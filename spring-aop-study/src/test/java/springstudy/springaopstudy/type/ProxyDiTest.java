package springstudy.springaopstudy.type;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springstudy.springaopstudy.member.MemberService;
import springstudy.springaopstudy.member.MemberServiceImpl;
import springstudy.springaopstudy.type.aop.ProxyDiAspect;

@Slf4j
@Import({ProxyDiAspect.class})
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@SpringBootTest
public class ProxyDiTest {

    @Autowired MemberService memberService;
    @Autowired MemberServiceImpl memberServiceImpl;

    @Test
    void ex1(){
        log.info("memberService.getClass() = {}", memberService.getClass());
        // 오류 발생
//        log.info("memberServiceImpl.getClass() = {}", memberServiceImpl.getClass());

//        memberServiceImpl.hello("helloA");
    }

    @Test
    void ex2(){
        log.info("memberService.getClass() = {}", memberService.getClass());
        log.info("memberServiceImpl.getClass() = {}", memberServiceImpl.getClass());

        memberServiceImpl.hello("helloA");
    }
}
