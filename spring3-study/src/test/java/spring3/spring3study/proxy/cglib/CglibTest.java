package spring3.spring3study.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import spring3.spring3study.proxy.cglib.code.TimeMethodInterceptor;
import spring3.spring3study.proxy.common.service.ConcreteService;

@Slf4j
public class CglibTest {

    @Test
    void cglib(){
        ConcreteService target = new ConcreteService();

        // cglib 생성
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();

        proxy.call();

        log.info("targetClass = {}", target.getClass());
        log.info("ProxyClass = {}", proxy.getClass());
    }
}
