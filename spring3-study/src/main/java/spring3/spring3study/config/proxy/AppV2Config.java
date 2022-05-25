package spring3.spring3study.config.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring3.spring3study.app.proxy.v1.*;
import spring3.spring3study.app.proxy.v2.OrderControllerV2;
import spring3.spring3study.app.proxy.v2.OrderRepositoryV2;
import spring3.spring3study.app.proxy.v2.OrderServiceV2;

@Configuration
public class AppV2Config {

    @Bean
    public OrderControllerV2 createOrderControllerV2(){return new OrderControllerV2(createOrderServiceV2());}

    @Bean
    public OrderServiceV2 createOrderServiceV2() {
        return new OrderServiceV2(createOrderRepositoryV2());
    }

    @Bean
    public OrderRepositoryV2 createOrderRepositoryV2() {
        return new OrderRepositoryV2();
    }
}
