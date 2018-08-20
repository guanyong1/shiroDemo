package com.shiro.demo.controller;

import com.shiro.demo.bean.UserBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 10:58 2018/4/11
 * @Modified By:
 */
@Controller
public class UserController {

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
    @ResponseBody
    public String login(UserBean user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPwd());
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            return e.getMessage();
        }

        return "登录成功";
    }
}
