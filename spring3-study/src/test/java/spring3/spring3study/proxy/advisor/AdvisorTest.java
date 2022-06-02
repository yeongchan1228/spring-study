package spring3.spring3study.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import spring3.spring3study.proxy.common.advice.TimeAdvice;
import spring3.spring3study.proxy.common.service.ServiceImpl;
import spring3.spring3study.proxy.common.service.ServiceInterface;

import java.lang.reflect.Method;

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

    @Test
    @DisplayName("직접 만든 PointCut")
    public void advisorTest2() throws Exception {
        // given
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // Advice는 Class, Method Filter가 둘 다 참인 경우 동작
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        // when
        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("Spring이 제공하는 PointCut")
    public void advisorTest3() throws Exception {
        // given
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        NameMatchMethodPointcut methodPointcut = new NameMatchMethodPointcut();
        methodPointcut.addMethodName("save");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(methodPointcut, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        // when
        proxy.save();
        proxy.find();
    }

    static class MyPointCut implements Pointcut{

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher{

        private String matchName = "save";

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            log.info("PointCut 호출 method={}, targetClass = {}", method.getName(), targetClass);
            return method.getName().equals(matchName);
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}
