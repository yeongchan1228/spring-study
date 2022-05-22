package spring2.spring2Study.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring2.spring2Study.AppConfig;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    AppConfig appConfig = new AppConfig();

    MemberService memberService = appConfig.memberService();

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        assertThat(member).isEqualTo(findMember);
    }
}
