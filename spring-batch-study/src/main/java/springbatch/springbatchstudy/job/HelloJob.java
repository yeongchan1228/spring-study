package springbatch.springbatchstudy.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean("helloBatchJob")
    public Job helloBatchJob(Step helloStep) {
        return jobBuilderFactory.get("helloBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(helloStep)
                .build();
    }

    @JobScope
    @Bean("helloStep")
    public Step helloStep(Tasklet tasklet) {
        return stepBuilderFactory.get("helloStep")
                .tasklet(tasklet)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("Hello Spring Batch");
            return RepeatStatus.FINISHED;
        };
    }
}
