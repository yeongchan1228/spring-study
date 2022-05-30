package spring3.spring3study.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring3.spring3study.proxy.jdkdynamic.code.*;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA(){
        AInterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy =
                (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        proxy.call();
        log.info("Target Class = {}", target.getClass());
        log.info("Proxy Class = {}", proxy.getClass());
    }

    @Test
    void dynamicB(){
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // Proxy는 handler 로직을 수행한다.
        BInterface proxy
                = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call();
        String result = proxy.call2();
        log.info("result = {}", result);
    }
}
