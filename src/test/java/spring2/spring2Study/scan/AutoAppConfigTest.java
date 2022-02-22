package spring2.spring2Study.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring2.spring2Study.AutoAppConfig;
import spring2.spring2Study.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
        void basicScan(){
            AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
            MemberService memberService = ac.getBean(MemberService.class);

            assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
