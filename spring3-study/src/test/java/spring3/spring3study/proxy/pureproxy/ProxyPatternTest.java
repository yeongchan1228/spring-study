package spring3.spring3study.proxy.pureproxy;

import org.junit.jupiter.api.Test;
import spring3.spring3study.proxy.pureproxy.code.CacheProxy;
import spring3.spring3study.proxy.pureproxy.code.ProxyPatternClient;
import spring3.spring3study.proxy.pureproxy.code.RealSubject;

public class ProxyPatternTest {

    @Test
    void noProxyTest(){
        ProxyPatternClient client = new ProxyPatternClient(new RealSubject());
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void proxyTest(){
        ProxyPatternClient client = new ProxyPatternClient(new CacheProxy(new RealSubject()));
        client.execute();
        client.execute();
        client.execute();
    }
}
