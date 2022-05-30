package spring3.spring3study.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements AInterface{
    @Override
    public String call() {
        log.info("A Call");
        return "A";
    }
}
