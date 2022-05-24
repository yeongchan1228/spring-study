package spring3.spring3study.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring3.spring3study.template.code.AbstractTemplate;
import spring3.spring3study.template.code.SubClassLogic1;
import spring3.spring3study.template.code.SubClassLogic2;

@Slf4j
public class TemplateMethodTest {

    @Test
    public void templateMethodV0() throws Exception {
        logic1();
        logic2();
    }

    /**
     * 추상 템플릿 적용
     */
    @Test
    public void templateMethodV1() throws Exception {
        AbstractTemplate abstractTemplate = new SubClassLogic1();
        abstractTemplate.execute();

        abstractTemplate = new SubClassLogic2();
        abstractTemplate.execute();
    }

    /**
     * 익명 내부 클래스 사용
     */
    @Test
    public void templateMethodV2() throws Exception {
        AbstractTemplate abstractTemplate = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        abstractTemplate.execute();

        abstractTemplate = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        abstractTemplate.execute();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();

        log.info("비지니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();

        log.info("비지니스 로직2 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
