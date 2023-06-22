package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
//스프링 컨테이너에 관련 컨트롤러가 있는지 찾고 없으면 static 파일을 찾도록 되어있다.