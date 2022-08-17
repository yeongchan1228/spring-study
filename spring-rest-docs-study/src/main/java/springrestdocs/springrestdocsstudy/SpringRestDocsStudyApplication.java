package springrestdocs.springrestdocsstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springrestdocs.springrestdocsstudy.member.MemberRepository;

@EnableJpaAuditing
@SpringBootApplication
public class SpringRestDocsStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestDocsStudyApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public TestDataInit testDataInit(MemberRepository memberRepository) {
		return new TestDataInit(memberRepository);
	}
}
