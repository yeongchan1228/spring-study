package spring3.spring3study.proxy.proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import spring3.spring3study.proxy.common.advice.TimeAdvice;
import spring3.spring3study.proxy.common.service.ConcreteService;
import spring3.spring3study.proxy.common.service.ServiceImpl;
import spring3.spring3study.proxy.common.service.ServiceInterface;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("interface 존재 시 Jdk Dynamic Proxy 사용")
    void interfaceProxy(){
        ServiceInterface service = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();

        // ProxyFactory를 사용해서 만들었을 때만 적용 가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("interface 없을 시 CGLIB 사용")
    void noInterfaceProxy(){
        ConcreteService concreteService = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(concreteService);
        proxyFactory.addAdvice(new TimeAdvice());

        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        proxy.call();

        // ProxyFactory를 사용해서 만들었을 때만 적용 가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("ProxyTargetClass 사용, interface 존재해도 Class 기반 Proxy 생성")
    void proxyTargetClass(){
        ServiceInterface service = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvice(new TimeAdvice());
        proxyFactory.setProxyTargetClass(true);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();

        // ProxyFactory를 사용해서 만들었을 때만 적용 가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}
