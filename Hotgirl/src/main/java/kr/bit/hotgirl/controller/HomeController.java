package kr.bit.hotgirl.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "/maps/maptest";
    }

}
