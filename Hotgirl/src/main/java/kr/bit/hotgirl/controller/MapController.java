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
    @GetMapping("/maptest22")
    public String maptest22(){
        return "maps/maptest22";
    }

    @GetMapping("/mypage")
    public String mypage(){
        return "/mypage";
    }

    @GetMapping("/jsimport")
    public String jsimport(){
        return "maps/jsimport";
    }


}
