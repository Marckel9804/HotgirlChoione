package kr.bit.hotgirl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chater")
    public String chater(){
        return "chat/chater";
    }

    @GetMapping("/maptest")
    public String mapTest() {
        return "chat/maptest";
    }
}
