package spring3.spring3study.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring3.spring3study.strategy.code.Strategy;
import spring3.spring3study.strategy.code.StrategyLogic1;
import spring3.spring3study.strategy.code.StrategyLogic2;
import spring3.spring3study.strategy.code.v1.ContextV1;
import spring3.spring3study.strategy.code.v2.ContextV2;

/**
 * 전략 패턴
 */
@Slf4j
public class ContextV1Test {

    @Test
    public void test() throws Exception {
        logic1();
        logic2();
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

    @Test
    public void strategyV1() throws Exception {
        ContextV1 contextV1 = new ContextV1(new StrategyLogic1());
        contextV1.execute();

        contextV1 = new ContextV1(new StrategyLogic2());
        contextV1.execute();
    }

    /**
     * 익명 클래스로 구현
     */
    @Test
    public void strategyV1_1() throws Exception {
        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        contextV1.execute();

        contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
        contextV1.execute();
    }

    /**
     * 람다식 구현
     */
    @Test
    public void strategyV1_2() throws Exception {
        ContextV1 contextV1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        contextV1.execute();

        contextV1 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
        contextV1.execute();
    }

    @Test
    public void strategyV2() throws Exception {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() -> log.info("비지니스 로직1 실행"));
        contextV2.execute(() -> log.info("비지니스 로직2 실행"));
    }

}
