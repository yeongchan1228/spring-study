package spring3.spring3study.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring3.spring3study.trace.threadlocal.code.ThreadLocalService;

@Slf4j
public class ThreadLocalServiceTest {

    ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    public void field() throws Exception {
        log.info("main start");
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };
        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        threadB.start();
        threadB.join();

        log.info("main exit");
    }

    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
