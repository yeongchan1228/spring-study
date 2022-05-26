package spring3.spring3study.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;
import spring3.spring3study.proxy.pureproxy.proxy.code.CacheProxy;
import spring3.spring3study.proxy.pureproxy.proxy.code.ProxyPatternClient;
import spring3.spring3study.proxy.pureproxy.proxy.code.RealSubject;

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
