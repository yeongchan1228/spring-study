package springbatch.springbatchstudy.job.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import springbatch.springbatchstudy.BatchTestConfig;
import springbatch.springbatchstudy.core.service.PlayerSalaryService;

@SpringBootTest
@SpringBatchTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {FlatFileJobConfig.class, BatchTestConfig.class, PlayerSalaryService.class})
class FlatFileJobConfigTest {

    @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void success() throws Exception {
        // when
        JobExecution execution = jobLauncherTestUtils.launchJob();

        // then
        Assertions.assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        AssertFile.assertFileEquals(new FileSystemResource("player-salary-list.txt"),
                new FileSystemResource("success-player-salary-list.txt"));
    }

}