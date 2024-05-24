package kr.bit.hotgirl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeController {
    @GetMapping("login/login")
    public String login() {
        return "login/login";
    }
    @GetMapping("login/mypage")
    public String mypage() {
        return "login/mypage";
    }
}
