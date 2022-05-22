package spring2.spring2Study.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 클래스 앞에 붙는 어노테이션이다.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
