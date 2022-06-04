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

    @Test
    @DisplayName("부모 타입으로 매칭")
    public void superTypeMatch() throws Exception {
        pointcut.setExpression("execution(* springstudy.springaopstudy.member.MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("Not Override Method 매칭")
    public void typeMatchInternal() throws Exception {
        pointcut.setExpression("execution(* springstudy.springaopstudy.member.MemberService.*(..))");
        Method internal = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isFalse();

        pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* springstudy.springaopstudy.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isTrue();
    }
    
    @Test
    @DisplayName("args Match")
    public void argsMatch() throws Exception {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("noArgs Match")
    public void noArgsMatchFalse() throws Exception {
        pointcut.setExpression("execution(* *())"); // 파라미터가 아무 것도 없어야 하는데 hello는 String이 존재한다.
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("파라미터가 한 개, 종류 무관")
    public void noArgsMatch() throws Exception {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("파라미터 수 무관, 종류 무관")
    public void noArgsMatch2() throws Exception {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("파라미터 수 2개, 종류 String 시작, 이 후 무관")
    public void noArgsMatch3False() throws Exception {
        pointcut.setExpression("execution(* *(String, *))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("파라미터 수 무관, 종류 String 시작, 이 후 무관")
    public void noArgsMatch4() throws Exception {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
