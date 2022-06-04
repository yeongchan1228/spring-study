package springstudy.springaopstudy.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // Runtime으로 해야 동적으로 확인할 수 있다.
public @interface MethodAop {
    String value();
}
