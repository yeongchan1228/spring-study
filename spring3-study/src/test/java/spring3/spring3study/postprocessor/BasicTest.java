package spring3.spring3study.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class BasicTest {
    
    
    @Test
    public void basicConfig() {
        // Spring Container
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        A a = applicationContext.getBean("beanA", A.class);

        assertThat(a).isInstanceOf(A.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean(B.class));
    }

    @Slf4j
    @Configuration
    static class BasicConfig{
        @Bean(name = "beanA")
        public A a(){return new A();}


        public B b(){return new B();}
    }

    @Slf4j
    static class A{
        public void helloA() {
            log.info("helloA");
        }
    }

    @Slf4j
    static class B{
        public void helloB() {
            log.info("helloB");
        }
    }
}
