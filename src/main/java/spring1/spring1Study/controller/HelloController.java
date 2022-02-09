package spring1.spring1Study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // html url를 임의로 치는 것
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // hello.html 리턴함 = resources -> templates/hello.html 매핑(ViewResolver)
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){ // mvc 방식
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name")String name){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello hellpApi(@RequestParam(value = "name")String name){ // 객체 전달
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}

