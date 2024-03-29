package springbatch.springbatchstudy;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchStudyApplication.class, args);
	}

}
