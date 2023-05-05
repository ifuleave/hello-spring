package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //localhost로 들어오면 home이 호출
    @GetMapping("/")
    public String Home(){
        return "home";
    }


}
