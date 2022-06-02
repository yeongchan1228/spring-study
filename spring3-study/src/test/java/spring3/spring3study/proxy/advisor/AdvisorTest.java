package spring3.spring3study.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import spring3.spring3study.proxy.common.advice.TimeAdvice;
import spring3.spring3study.proxy.common.service.ServiceImpl;
import spring3.spring3study.proxy.common.service.ServiceInterface;

@Slf4j
public class AdvisorTest {

    @Test
    public void advisorTest1() throws Exception {
        // given
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        // when
        proxy.save();
        proxy.find();
    }
}
