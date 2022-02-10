package spring1.spring1Study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring1.spring1Study.repository.MemberRepository;
import spring1.spring1Study.repository.MemoryMemberRepository;
import spring1.spring1Study.service.MemberService;

@Configuration // 자바 코드로 스프링 빈 등록할 수 있는 내용이 담긴 config
public class SpringConfig {

    @Bean // 스프링 빈에 등록해라
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
