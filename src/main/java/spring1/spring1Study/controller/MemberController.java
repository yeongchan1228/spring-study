package spring1.spring1Study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring1.spring1Study.service.MemberService;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에 있는 memberSerive 객체를 가져와서 넣어줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
