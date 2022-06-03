package spring3.spring3study.config.proxy.v5_autoproxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
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

//    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedNames("request*", "save*", "order*");
        return new DefaultPointcutAdvisor(nameMatchMethodPointcut, new LogTraceAdvice(logTrace));
    }

    /**
     * 패키지 중심 Pointcut 적용
     */
//    @Bean
    public Advisor advisor2(LogTrace logTrace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* spring3.spring3study.app..*(..))");

        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
    }

    /**
     * 패키지 + 메서드 이름 중심 Pointcut 적용
     */
    @Bean
    public Advisor advisor3(LogTrace logTrace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* spring3.spring3study.app..*(..)) " +
                "&& !execution(* spring3.spring3study.app..noLog(..))");

        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
    }
}
