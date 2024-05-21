package kr.bit.hotgirl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/")
    public String home() {
        return "/login/user/register";
    }
}
