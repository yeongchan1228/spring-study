package springstudy.springaopstudy.type;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import springstudy.springaopstudy.member.MemberService;
import springstudy.springaopstudy.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy(){
        MemberServiceImpl memberService = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(memberService);
        proxyFactory.setProxyTargetClass(false); // JDK Dynamic Proxy
        Object jdkProxy = proxyFactory.getProxy();

        MemberService memberServiceProxy = (MemberService) jdkProxy;

        assertThatThrownBy(() -> {MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) jdkProxy;})
                .isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy(){
        MemberServiceImpl memberService = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(memberService);
        proxyFactory.setProxyTargetClass(true); // JDK Dynamic Proxy
        Object cglibProxy = proxyFactory.getProxy();

        MemberService memberServiceProxy = (MemberService) cglibProxy;
        MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) cglibProxy;
    }
}
