package spring3.spring3study.proxy.pureproxy.concreteproxy;

import org.junit.jupiter.api.Test;
import spring3.spring3study.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import spring3.spring3study.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import spring3.spring3study.proxy.pureproxy.concreteproxy.code.TimeProxy;

public class ConcreteProxyTest {

    @Test
    void noProxy(){
        ConcreteClient concreteClient = new ConcreteClient(new ConcreteLogic());
        concreteClient.execute();
    }

    @Test
    void proxy(){
        ConcreteClient concreteClient = new ConcreteClient(new TimeProxy(new ConcreteLogic()));
        concreteClient.execute();
    }
}
