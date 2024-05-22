package kr.bit.hotgirl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/maps")
@Controller
public class MapController {
    @GetMapping("/maptest")
    public String maptest(){
        return "maps/maptest";
    }
    @GetMapping("/maptest11")
    public String maptest11(){
        return "maps/maptest11";
    }

    @GetMapping("/mypage")
    public String mypage(){
        return "login/mypage";
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

}
