package springstudy.springaopstudy.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springstudy.springaopstudy.member.annotation.ClassAop;
import springstudy.springaopstudy.member.annotation.MethodAop;

@Slf4j
@Service
@ClassAop
public class MemberServiceImpl implements MemberService{

    @Override
    @MethodAop("test_value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param){
        return "ok";
    }
}
