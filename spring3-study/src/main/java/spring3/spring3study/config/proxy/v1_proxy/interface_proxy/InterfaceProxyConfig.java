package spring3.spring3study.config.proxy.v1_proxy.interface_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring3.spring3study.app.proxy.v1.*;
import spring3.spring3study.trace.logtrace.LogTrace;
import spring3.spring3study.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        return new OrderControllerInterfaceProxy(new OrderControllerImpl(orderService(logTrace)), logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        return new OrderServiceInterfaceProxy(new OrderServiceV1Impl(orderRepository(logTrace)), logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        return new OrderRepositoryInterfaceProxy(new OrderRepositoryV1Impl(), logTrace);
    }

}