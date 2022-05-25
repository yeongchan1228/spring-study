package spring3.spring3study.strategy.code.v1;

import lombok.extern.slf4j.Slf4j;
import spring3.spring3study.strategy.code.Strategy;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();

        strategy.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
