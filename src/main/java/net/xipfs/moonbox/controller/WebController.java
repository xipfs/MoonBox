package net.xipfs.moonbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  跳转显示页面
 */
@Controller
public class WebController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/ai")
    public String ai() {
        return "ai";
    }
}
