package spring3.spring3study.strategy.template.collback.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(TimeLogCallBack callBack){
        long startTime = System.currentTimeMillis();

        callBack.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
