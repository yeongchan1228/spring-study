package springbatch.springbatchstudy.job;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import springbatch.springbatchstudy.BatchTestConfig;
import springbatch.springbatchstudy.core.domain.PlainText;
import springbatch.springbatchstudy.core.repository.PlainTextRepository;
import springbatch.springbatchstudy.core.repository.ResultTextRepository;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {PlainTextJobConfig.class, BatchTestConfig.class})
class PlainTextConfigTest {

    @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired private PlainTextRepository plainTextRepository;
    @Autowired private ResultTextRepository resultTextRepository;

    @Test
    @Rollback
    public void success_givenNoPlainText() throws Exception {
        JobExecution execution = jobLauncherTestUtils.launchJob();

        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        assertThat(resultTextRepository.count()).isEqualTo(0);
    }

    @Test
    @Rollback
    public void success_givenPlainTexts() throws Exception {
        // given
        givenPlainTexts(12);

        // when
        JobExecution execution = jobLauncherTestUtils.launchJob();

        // then
        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        assertThat(resultTextRepository.count()).isEqualTo(12);
    }

    private void givenPlainTexts(Integer count) {
        IntStream.range(0, count)
                .forEach(num -> plainTextRepository.save(new PlainText(null, "text" + num)));
    }
}