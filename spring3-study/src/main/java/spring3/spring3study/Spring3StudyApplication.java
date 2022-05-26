package spring3.spring3study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import spring3.spring3study.config.proxy.AppV1Config;
import spring3.spring3study.config.proxy.AppV2Config;

@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "spring3.spring3study.app.proxy")
public class Spring3StudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring3StudyApplication.class, args);
	}

}
