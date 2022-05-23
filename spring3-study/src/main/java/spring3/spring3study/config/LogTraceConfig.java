package spring3.spring3study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring3.spring3study.trace.logtrace.FieldLogTrace;
import spring3.spring3study.trace.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace createLogTrace(){
        return new FieldLogTrace();
    }
}
