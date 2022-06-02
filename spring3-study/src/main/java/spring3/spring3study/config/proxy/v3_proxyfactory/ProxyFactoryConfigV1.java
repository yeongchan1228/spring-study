package spring3.spring3study.config.proxy.v3_proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring3.spring3study.app.proxy.v1.*;
import spring3.spring3study.config.proxy.v3_proxyfactory.advice.LogTraceAdvice;
import spring3.spring3study.trace.logtrace.LogTrace;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace){
        OrderControllerImpl target = new OrderControllerImpl(orderServiceV1(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        return (OrderControllerV1) proxyFactory.getProxy();
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace){
        OrderServiceV1Impl target = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        return (OrderServiceV1) proxyFactory.getProxy();
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace){
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        return (OrderRepositoryV1) proxyFactory.getProxy();
    }

    private DefaultPointcutAdvisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedNames("request*", "order*", "save*");
        return new DefaultPointcutAdvisor(nameMatchMethodPointcut, new LogTraceAdvice(logTrace));
    }
}
