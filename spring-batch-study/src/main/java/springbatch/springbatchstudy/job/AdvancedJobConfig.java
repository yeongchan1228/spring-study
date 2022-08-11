package springbatch.springbatchstudy.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbatch.springbatchstudy.job.validator.LocalDateParameterValidator;

import java.time.LocalDate;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AdvancedJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job advancedJog(JobExecutionListener jobExecutionListener, Step advancedStep) {
        return jobBuilderFactory.get("advancedJob")
                .incrementer(new RunIdIncrementer())
                .validator(new LocalDateParameterValidator("targetDate"))
                .listener(jobExecutionListener)
                .start(advancedStep)
                .build();
    }

    @Bean
    @JobScope
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("[JobExecutionListener#beforeJob] jonExecution - " + jobExecution.getExitStatus());
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.FAILED) {
                    // Batch 작업 실패 시 다른 처리 가능
                    log.error("[JobExecutionListener#afterJob] jonExecution is FAILED!!! - " + jobExecution.getExitStatus());
                }
            }
        };
    }

    @Bean
    @JobScope
    public Step advancedStep(@Value("#{jobParameters['targetDate']}") String targetDate) {
        return stepBuilderFactory.get("advancedStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("[advancedTasklet] = targetDate - " + targetDate);
                    log.info("[advancedTasklet] executed advancedTasklet");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
