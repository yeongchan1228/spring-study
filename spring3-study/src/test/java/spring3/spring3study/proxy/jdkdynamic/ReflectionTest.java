package spring3.spring3study.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0(){
        Hello target = new Hello();

        // 공통 로직 1 시작
        log.info("start");
        String resultA = target.callA();
        log.info("result = {}", resultA);
        // 공통 로직 1 종료

        // 공통 로직 2 시작
        log.info("start");
        String resultB = target.callB();
        log.info("result = {}", resultB);
        // 공통 로직 2 종료
    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보 획득
        // 내부에 클래스가 존재할 땐 $로 접근
        Class classHello = Class.forName("spring3.spring3study.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // 메서드A 정보 획득
        Method methodA = classHello.getMethod("callA");
        // target instance에 있는 method 호출
        Object result1 = methodA.invoke(target);
        log.info("result1 = {}", result1);

        // 메서드B 정보 획득
        Method methodB = classHello.getMethod("callB");
        // target instance에 있는 method 호출
        Object result2 = methodB.invoke(target);
        log.info("result2 = {}", result2);
    }

    @Test
    void reflection2() throws Exception {
        // 클래스 정보 획득
        // 내부에 클래스가 존재할 땐 $로 접근
        Class classHello = Class.forName("spring3.spring3study.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        dynamicCall(classHello.getMethod("callA"), target);
        dynamicCall(classHello.getMethod("callB"), target);
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result = {}", result);
    }

    @Slf4j
    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }

        public String callB(){
            log.info("callB");
            return "B";
        }
    }
}
