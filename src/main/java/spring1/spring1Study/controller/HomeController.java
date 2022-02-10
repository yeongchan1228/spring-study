package spring1.spring1Study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // localhost8080으로 접속하면 이 것이 먼저 실행된다.
    public String home(){
        return "home";
    }
}
