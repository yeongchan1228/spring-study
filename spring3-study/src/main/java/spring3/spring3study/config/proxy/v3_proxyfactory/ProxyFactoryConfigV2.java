package spring3.spring3study.config.proxy.v3_proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring3.spring3study.app.proxy.v2.OrderControllerV2;
import spring3.spring3study.app.proxy.v2.OrderRepositoryV2;
import spring3.spring3study.app.proxy.v2.OrderServiceV2;
import spring3.spring3study.config.proxy.v3_proxyfactory.advice.LogTraceAdvice;
import spring3.spring3study.trace.logtrace.LogTrace;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace){
        OrderControllerV2 target = new OrderControllerV2(orderServiceV2(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        return (OrderControllerV2) proxyFactory.getProxy();
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace){
        OrderServiceV2 target = new OrderServiceV2(orderRepositoryV2(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        return (OrderServiceV2) proxyFactory.getProxy();
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace){
        OrderRepositoryV2 target = new OrderRepositoryV2();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        return (OrderRepositoryV2) proxyFactory.getProxy();
    }

    private DefaultPointcutAdvisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedNames("request*", "order*", "save*");
        return new DefaultPointcutAdvisor(nameMatchMethodPointcut, new LogTraceAdvice(logTrace));
    }
}
