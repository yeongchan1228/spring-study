package spring1.spring1Study.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import spring1.spring1Study.domain.Member;
import spring1.spring1Study.repository.MemberRepository;
import spring1.spring1Study.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트 결과가 DB에 남지 않음, 롤백한다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Commit // 해당 결과를 DB에 커밋한다.
    void join() {
        //given
        Member member = new Member();
        member.setName("string");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(member.getId()).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원(){
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}