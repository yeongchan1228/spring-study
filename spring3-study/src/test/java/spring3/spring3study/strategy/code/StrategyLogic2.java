package spring3.spring3study.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic2 implements Strategy{
    @Override
    public void call() {
        log.info("비지니스 로직2 실행");
    }
}
