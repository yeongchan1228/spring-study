package spring3.spring3study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import spring3.spring3study.config.proxy.AppV1Config;
import spring3.spring3study.config.proxy.AppV2Config;
import spring3.spring3study.config.proxy.v1_proxy.concrete_proxy.ConcreteProxyConfig;
import spring3.spring3study.config.proxy.v1_proxy.interface_proxy.InterfaceProxyConfig;
import spring3.spring3study.config.proxy.v2_dynamicproxy.DynamicProxyBasicConfig;
import spring3.spring3study.config.proxy.v2_dynamicproxy.DynamicProxyFilterConfig;
import spring3.spring3study.trace.logtrace.LogTrace;
import spring3.spring3study.trace.logtrace.ThreadLocalLogTrace;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import({InterfaceProxyConfig.class})
//@Import({ConcreteProxyConfig.class})
//@Import(value = {DynamicProxyBasicConfig.class})
@Import(value = {DynamicProxyFilterConfig.class})
@SpringBootApplication(scanBasePackages = "spring3.spring3study.app.proxy")
public class Spring3StudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring3StudyApplication.class, args);
	}


	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}
}
