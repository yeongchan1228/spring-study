package spring3.spring3study.config.proxy.v6_aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring3.spring3study.config.proxy.AppV1Config;
import spring3.spring3study.config.proxy.AppV2Config;
import spring3.spring3study.config.proxy.v6_aop.aspect.LogTraceAspect;
import spring3.spring3study.trace.logtrace.LogTrace;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace){
        return new LogTraceAspect(logTrace);
    }
}
