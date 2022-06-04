package springstudy.springaopstudy.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springstudy.springaopstudy.internalcall.aop.CallLogAspect;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import({CallLogAspect.class})
@SpringBootTest
class CallServiceV2Test {

    @Autowired CallServiceV2 callServiceV2;

    @Test
    void external() {
        callServiceV2.external();
    }
}