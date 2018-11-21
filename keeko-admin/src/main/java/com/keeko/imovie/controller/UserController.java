package com.keeko.imovie.controller;

import com.keeko.imovie.entity.User;
import com.keeko.imovie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/user")
    public String testIndex(String id){
        User user = userService.selectByPrimaryKey(id);
        System.out.println("访问成功");
        return "/index";
    }

   /* @RequestMapping
    String home(){
        System.out.println("访问成功 home");
        return "/index";
    }*/

}
