package spring3.spring3study.config.proxy.v1_proxy.concrete_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring3.spring3study.app.proxy.v2.OrderControllerV2;
import spring3.spring3study.app.proxy.v2.OrderRepositoryV2;
import spring3.spring3study.app.proxy.v2.OrderServiceV2;
import spring3.spring3study.trace.logtrace.LogTrace;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace){
        return new OrderControllerConcreteProxy(new OrderControllerV2(orderServiceV2(logTrace)), logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace){
        return new OrderServiceConcreteProxy(new OrderServiceV2(orderRepositoryV2(logTrace)), logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace){
        return new OrderRepositoryConcreteProxy(new OrderRepositoryV2(), logTrace);
    }
}
