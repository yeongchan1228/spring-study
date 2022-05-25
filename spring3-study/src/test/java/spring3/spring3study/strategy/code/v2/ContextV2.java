package spring3.spring3study.strategy.code.v2;

import lombok.extern.slf4j.Slf4j;
import spring3.spring3study.strategy.code.Strategy;

/**
 * 파라미터에 전략을 보관
 */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();

        strategy.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
