package com.shiro.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 10:58 2018/4/11
 * @Modified By:
 */
@Controller
public class UserController {

    @RequestMapping("/userLogin")
    public String login(){
        System.out.println(11111);
        return "index.html";
    }
}
