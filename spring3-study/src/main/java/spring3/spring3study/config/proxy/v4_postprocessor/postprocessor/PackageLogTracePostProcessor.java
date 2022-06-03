package spring3.spring3study.config.proxy.v4_postprocessor.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String basePackage;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("bean = {}, beanName = {}", bean.getClass(), beanName);

        String packageName = bean.getClass().getPackageName();
        if(packageName.startsWith(basePackage)) {
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvisor(advisor);
            return proxyFactory.getProxy();
        }

        return bean;
    }
}
