package springbatch.springbatchstudy.job.parallel;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import springbatch.springbatchstudy.dto.AmountDto;

import java.io.File;
import java.io.IOException;

/**
 * 단일 프로세스 내에서 청크 단위로 병렬 처리
 */
@Configuration
@RequiredArgsConstructor
public class MultiThreadStepJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job multiThreadJob(Step amountFileStep) {
        return jobBuilderFactory.get("multiThreadJob")
                .incrementer(new RunIdIncrementer())
                .start(amountFileStep)
                .build();
    }

    @Bean
    @JobScope
    public Step amountFileStep(FlatFileItemReader amountFileItemReader,
                               ItemProcessor itemProcessor,
                               FlatFileItemWriter amountFileItemWriter,
                               TaskExecutor taskExecutor) {
        return stepBuilderFactory.get("amountFileStep")
                .<AmountDto, AmountDto>chunk(10)
                .reader(amountFileItemReader)
                .processor(itemProcessor)
                .writer(amountFileItemWriter)
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring-batch-task-executor");
    }

    @Bean
    @StepScope
    public FlatFileItemReader amountFileItemReader() {
        return new FlatFileItemReaderBuilder()
                .name("amountFileItemReader")
                .lineTokenizer(new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB))
                .fieldSetMapper(new AmountFieldSetMapper())
                .resource(new FileSystemResource("data/input.txt"))
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<AmountDto, AmountDto> itemProcessor() {
        return item -> {
            System.out.println("Thread -> " + Thread.currentThread().getName());
            item.setAmount(item.getAmount() * 100);
            return item;
        };
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AmountDto> amountFileItemWriter() throws IOException {
        BeanWrapperFieldExtractor<AmountDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"index", "name", "amount"});
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<AmountDto> aggregator = new DelimitedLineAggregator<>();
        aggregator.setFieldExtractor(fieldExtractor);

        new File("data/output.txt").createNewFile();
        FileSystemResource resource = new FileSystemResource("data/output.txt");

        return new FlatFileItemWriterBuilder()
                .name("amountFileItemWriter")
                .lineAggregator(aggregator)
                .resource(resource)
                .build();

    }
}
