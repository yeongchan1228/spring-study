package spring3.spring3study.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import spring3.spring3study.proxy.common.advice.TimeAdvice;
import spring3.spring3study.proxy.common.service.ServiceImpl;
import spring3.spring3study.proxy.common.service.ServiceInterface;

@Slf4j
public class MultiAdvisorProxy {

    /**
     * 프록시를 여러 개 생성해야 한다는 단점
     */
    @Test
    @DisplayName("여러 Proxy 생성")
    public void multiAdvisorTest1() throws Exception {
        // given
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        proxyFactory.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory.getProxy();

        proxyFactory = new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory.getProxy();

        proxy2.save();
        proxy2.find();
    }

    @Test
    @DisplayName("하나의 Proxy, 여러 Advisor")
    public void multiAdvisorTest2() throws Exception {
        // given
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // Advisor 순서대로 적용해야 한다.
        proxyFactory.addAdvisor(advisor2);
        proxyFactory.addAdvisor(advisor1);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();

    }

    @Slf4j
    static class Advice1 implements MethodInterceptor{

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 호출");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor{

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 호출");
            return invocation.proceed();
        }
    }
}
