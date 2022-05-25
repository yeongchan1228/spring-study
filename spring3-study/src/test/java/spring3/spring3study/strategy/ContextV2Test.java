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
public class ContextV2Test {

    @Test
    public void strategyV2() throws Exception {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    @Test
    public void strategyV2_1() throws Exception {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new Strategy(){
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        contextV2.execute(new Strategy(){
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
    }

    @Test
    public void strategyV2_2() throws Exception {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() -> log.info("비지니스 로직1 실행"));
        contextV2.execute(() -> log.info("비지니스 로직2 실행"));
    }
}
