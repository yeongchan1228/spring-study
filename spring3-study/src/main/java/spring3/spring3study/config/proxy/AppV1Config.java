package spring3.spring3study.config.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring3.spring3study.app.proxy.v1.*;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV1 createOrderControllerV1(){
        return new OrderControllerImpl(createOrderServiceV1());
    }

    @Bean
    public OrderServiceV1 createOrderServiceV1() {
        return new OrderServiceV1Impl(createOrderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 createOrderRepositoryV1() {
        return new OrderRepositoryV1Impl();
    }
}
