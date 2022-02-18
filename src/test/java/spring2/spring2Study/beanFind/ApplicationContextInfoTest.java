package spring2.spring2Study.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring2.spring2Study.AppConfig;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 조회")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object : " + bean );
        }
    }

    @Test
    @DisplayName("애플리케이션(설정 파일에서 등록한) 빈 조회")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefiniton = ac.getBeanDefinition(beanDefinitionName);


            //ROLE_APPLICATION : 직접 등록한 스프링 빈
            //ROLE_INFRASTRUCTURE : 스프링 내부에서 사용하는 빈
            if(beanDefiniton.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefiniton + " object : " + bean);
            }
        }
    }
}
