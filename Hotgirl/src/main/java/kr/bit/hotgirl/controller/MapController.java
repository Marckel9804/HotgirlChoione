package kr.bit.hotgirl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/maps")
@Controller
public class MapController {









    @GetMapping("/api_test")
    public String apiTest() {
        return "maps/api_test";
    }

    @GetMapping("/imageMarker")
    public String imageMarker() {
        return "maps/imageMarker";
    }
}
