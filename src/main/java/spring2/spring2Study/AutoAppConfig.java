package spring2.spring2Study;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "spring2.spring2Study.member", // 컴포넌트 스캔 위치를 지정한다. 디폴트일 경우 ComponentScan이 붙은 클래스의
        // 패키지 전체를 찾는다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

}
