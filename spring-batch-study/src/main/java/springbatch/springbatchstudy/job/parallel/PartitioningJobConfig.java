package springbatch.springbatchstudy.job.parallel;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.SimplePartitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * 단일 프로세스에서 마스터 스텝과 워크 스텝을 두고, 마스터 스텝에서 생성해준 파티션 단위로 스텝을 병렬 처리한다.
 */
@Configuration
@RequiredArgsConstructor
public class PartitioningJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private static final int PARTITION_SIZE = 100; // 파티션 최대 사이즈

    @Bean
    public Job partitioningJob(Step masterStep) {
        return jobBuilderFactory.get("partitioningJob")
                .incrementer(new RunIdIncrementer())
                .start(masterStep)
                .build();
    }

    @Bean
    @JobScope
    public Step masterStep(Partitioner partitioner,
                           PartitionHandler partitionHandler) {
        return stepBuilderFactory.get("masterStep")
                .partitioner("anotherStep2", partitioner)
                .partitionHandler(partitionHandler)
                .build();
    }

    @Bean
    @StepScope
    public Partitioner partitioner() {
        SimplePartitioner simplePartitioner = new SimplePartitioner();
        simplePartitioner.partition(PARTITION_SIZE);
        return simplePartitioner;
    }

    @Bean
    @StepScope
    public TaskExecutorPartitionHandler partitionHandler(Step anotherStep2,
                                                         TaskExecutor taskExecutor3) {
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
        partitionHandler.setStep(anotherStep2);
        partitionHandler.setGridSize(PARTITION_SIZE);
        partitionHandler.setTaskExecutor(taskExecutor3);
        return partitionHandler;
    }

    @Bean
    public Step anotherStep2() {
        return stepBuilderFactory.get("anotherStep2")
                .tasklet(((contribution, chunkContext) -> {
                    Thread.sleep(500);
                    System.out.println("Another Step Completed,, Thread = " + Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor3() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor("spring-batch-task-executor");
        simpleAsyncTaskExecutor.setConcurrencyLimit(5);
        return simpleAsyncTaskExecutor;
    }

}
