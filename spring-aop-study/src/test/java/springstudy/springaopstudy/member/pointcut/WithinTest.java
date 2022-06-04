package springstudy.springaopstudy.member.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import springstudy.springaopstudy.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WithinTest {
     AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
     Method helloMethod;

     @BeforeEach
     public void init() throws NoSuchMethodException {
         helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
     }
     
    @Test
    @DisplayName("범위 내 모든 메서드 통과")
    public void withinExact1() throws Exception {
        pointcut.setExpression("within(springstudy.springaopstudy.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("범위 내 모든 메서드 통과")
    public void withinExact2() throws Exception {
        pointcut.setExpression("within(springstudy.springaopstudy.member.*Service*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("범위 내 모든 메서드 통과")
    public void withinExact3() throws Exception {
        pointcut.setExpression("within(springstudy.springaopstudy..*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("부모 지정 시 실패")
    public void withinExact4False() throws Exception {
        pointcut.setExpression("within(springstudy.springaopstudy.member.*Service)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }
}
