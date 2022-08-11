package springbatch.springbatchstudy.job.parallel;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import springbatch.springbatchstudy.dto.AmountDto;

/**
 * 단위 프로세스/멀티 스레드에서 Flow를 사용해 스텝을 동시에 실행한다.
 */
@Configuration
@RequiredArgsConstructor
public class ParallelStepJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parallelJob(Flow splitFlow) {
        return jobBuilderFactory.get("parallelJob")
                .incrementer(new RunIdIncrementer())
                .start(splitFlow)
                .build()
                .build();
    }

    @Bean
    @JobScope
    public Flow splitFlow(TaskExecutor taskExecutor2,
                          Flow flowAmountFileStep,
                          Flow flowAnotherStep) {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor2)
                .add(flowAmountFileStep, flowAnotherStep)
                .build();
    }

    @Bean
    public Flow flowAmountFileStep(Step amountFileStep2) {
        return new FlowBuilder<SimpleFlow>("flowAmountFileStep")
                .start(amountFileStep2)
                .end();
    }

    @Bean
    public Step amountFileStep2(FlatFileItemReader amountFileItemReader,
                               ItemProcessor itemProcessor,
                               FlatFileItemWriter amountFileItemWriter,
                               TaskExecutor taskExecutor) {
        return stepBuilderFactory.get("amountFileStep")
                .<AmountDto, AmountDto>chunk(10)
                .reader(amountFileItemReader)
                .processor(itemProcessor)
                .writer(amountFileItemWriter)
                .build();
    }

    @Bean
    public Flow flowAnotherStep(Step anotherStep) {
        return new FlowBuilder<SimpleFlow>("anotherStep")
                .start(anotherStep)
                .build();
    }

    @Bean
    public Step anotherStep() {
        return stepBuilderFactory.get("anotherStep")
                .tasklet(((contribution, chunkContext) -> {
                    Thread.sleep(500);
                    System.out.println("Another Step Completed,, Thread = " + Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor2() {
        return new SimpleAsyncTaskExecutor("spring-batch-task-executor");
    }
}
