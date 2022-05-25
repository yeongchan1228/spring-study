package spring3.spring3study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring3.spring3study.trace.logtrace.LogTrace;
import spring3.spring3study.trace.logtrace.ThreadLocalLogTrace;
import spring3.spring3study.trace.template.callback.TraceTemplate;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace createLogTrace(){
        return new ThreadLocalLogTrace();
    }

    @Bean
    public TraceTemplate createLogTemplate(){
        return new TraceTemplate(createLogTrace());
    }
}
