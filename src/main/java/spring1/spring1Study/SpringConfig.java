package spring1.spring1Study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring1.spring1Study.repository.MemberRepository;
import spring1.spring1Study.repository.SpringDataJpaMemeberRepository;
import spring1.spring1Study.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration // 자바 코드로 스프링 빈 등록할 수 있는 내용이 담긴 config
public class SpringConfig {

    private DataSource dataSource;
    private EntityManager em;
//    private final MemberRepository memberRepository;
    private final SpringDataJpaMemeberRepository jpaMemeberRepository;
    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository, SpringDataJpaMemeberRepository jpaMemeberRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.jpaMemeberRepository = jpaMemeberRepository;
//        this.memberRepository = memberRepository;
    }

    @Bean // 스프링 빈에 등록해라
    public MemberService memberService(){
        return new MemberService(jpaMemeberRepository);
    }

    /*@Bean
    public MemberRepository memberRepository(){
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
    }*/
}
