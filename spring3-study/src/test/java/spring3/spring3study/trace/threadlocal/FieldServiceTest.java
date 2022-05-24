package spring3.spring3study.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring3.spring3study.trace.threadlocal.code.FieldService;

@Slf4j
public class FieldServiceTest {

    FieldService fieldService = new FieldService();

    @Test
    public void field() throws Exception {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100); // 동시성 문제 X
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
