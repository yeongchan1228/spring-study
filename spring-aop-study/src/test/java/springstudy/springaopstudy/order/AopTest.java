package springstudy.springaopstudy.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springstudy.springaopstudy.order.aop.AspectV1;
import springstudy.springaopstudy.order.aop.AspectV2;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Import(value = {AspectV2.class})
public class AopTest {
    
    @Autowired OrderRepository orderRepository;
    @Autowired OrderService orderService;

    @Test
    public void aopInfo() throws Exception {
        log.info("isAopProxy, orderService = {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository = {}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    public void success() throws Exception {
        orderService.orderItem("itemA");
    }

    @Test
    public void exception() throws Exception {
        assertThatThrownBy(() -> orderService.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }
}