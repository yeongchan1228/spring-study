package spring3.spring3study.config.proxy.v5_autoproxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring3.spring3study.config.proxy.AppV1Config;
import spring3.spring3study.config.proxy.AppV2Config;
import spring3.spring3study.config.proxy.v3_proxyfactory.advice.LogTraceAdvice;
import spring3.spring3study.trace.logtrace.LogTrace;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedNames("request*", "save*", "order*");
        return new DefaultPointcutAdvisor(nameMatchMethodPointcut, new LogTraceAdvice(logTrace));
    }
}
