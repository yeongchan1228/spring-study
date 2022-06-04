package springstudy.springaopstudy.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /**
     * 생성자 주입은 생성하기 전 진행
     * Setter 주입은 생성이 다 끝난 후 진행
     * 2.6 이후 버전에서도 순환 참조를 아예 막음
     * spring.main.allow-circular-references=true을 사용해야 함.
     */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external(){
        log.info("call external");
        callServiceV1.internal(); // 내부 메서드 호출
    }

    public void internal(){
        log.info("call internal");
    }
}
