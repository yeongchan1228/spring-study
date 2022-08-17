package springrestdocs.springrestdocsstudy;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import springrestdocs.springrestdocsstudy.member.Member;
import springrestdocs.springrestdocsstudy.member.MemberRepository;

@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        memberRepository.save(Member.of("test1@naver.com", "test1"));
        memberRepository.save(Member.of("test2@naver.com", "test2"));
        memberRepository.save(Member.of("test3@naver.com", "test3"));
        memberRepository.save(Member.of("test4@naver.com", "test4"));
        memberRepository.save(Member.of("test5@naver.com", "test5"));
    }
}
