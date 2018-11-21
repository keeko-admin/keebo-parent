package com.keeko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/index")
    public String indexPage(){
        System.out.println("访问成功");
        return "index";
    }
}
