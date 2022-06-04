package springstudy.springaopstudy.member.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import springstudy.springaopstudy.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutionTest {
     AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
     Method helloMethod;

     @BeforeEach
     public void init() throws NoSuchMethodException {
         helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
     }
     
     @Test
     public void printMethod() throws Exception {
         // public java.lang.String springstudy.springaopstudy.member.MemberServiceImpl.hello(java.lang.String)
         log.info("helloMethod = {}", helloMethod);
     }

     @Test
     @DisplayName("가장 정확한 포인트 컷")
     public void exactMatch() throws Exception {
        pointcut.setExpression("execution(public String springstudy.springaopstudy.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
     }

    @Test
    @DisplayName("가장 많이 생략한 포인트 컷")
    public void allMatch() throws Exception {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("메서드 이름 매칭")
    public void nameMatch() throws Exception {
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("메서드 이름 패턴 매칭")
    public void namePatternMatch() throws Exception {
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("패키지 정확한 매칭")
    public void packageExactMatch() throws Exception {
        pointcut.setExpression("execution(* springstudy.springaopstudy.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("패키지 일부 생략 매칭")
    public void packageMatch() throws Exception {
        pointcut.setExpression("execution(* springstudy.springaopstudy.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("패키지 일부 생략 실패")
    public void packageMatchFalse() throws Exception {
         // MemberServiceImpl 안의 명시된 패키지 명까지 써야 한다.
        // package springstudy.springaopstudy.member;
        pointcut.setExpression("execution(* springstudy.springaopstudy.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("패키지 일부 생략 성공")
    public void packageMatchTrue() throws Exception {
        pointcut.setExpression("execution(* springstudy.springaopstudy..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
