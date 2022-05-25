package spring3.spring3study.strategy.template.collback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring3.spring3study.strategy.template.collback.code.TimeLogTemplate;

@Slf4j
public class TimeLogCallBackTest {

    /**
     * 템플릿 콜백 패턴
     */
    @Test
    public void callBackTest() throws Exception {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비즈니스 로직1 실행"));
    }
}
