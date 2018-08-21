package com.shiro.demo.controller;

import com.shiro.demo.bean.UserBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
        if(subject.hasRole("admin")){
            return "有admin权限";
        }
        return "无admin权限";
    }

    /**
     * @RequiresRoles("admin")使用shiro注解，只有admin角色可以访问该地址，括号内为数组，可传多个参数
     * @return
     */
    @RequiresRoles("admin")
    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    @ResponseBody
    public String testRole(){
        return "testRole";
    }

    /**
     * @RequiresPermissions("xxx")使用shiro注解，只有xxx权限可以访问该地址，括号内为数组，可传多个参数
     * @return
     */
    @RequiresPermissions("xxx")
    @RequestMapping(value = "/testPermission",method = RequestMethod.GET)
    @ResponseBody
    public String testPermission(){
        return "testPermission";
    }


    /**
     * shiro.xml文件中设置访问权限
     * @return
     */
    @RequestMapping(value = "/testPerms",method = RequestMethod.GET)
    @ResponseBody
    public String testPerms(){
        return "testPerms";
    }

    /**
     * xml文件中设置角色
     * @return
     */
    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    @ResponseBody
    public String testRole1(){
        return "testRole1";
    }


    /**
     * 在xml配置admin，admin1两个角色，需要同时有这个两个角色才能访问
     * @return
     */
    @RequestMapping(value = "/testRole2",method = RequestMethod.GET)
    @ResponseBody
    public String testRole2(){
        return "testRole2";
    }

    /**
     * 使用自定义filter，在xml中配置amdin,admin1两个角色，满足其中一个角色即可访问
     * @return
     */
    @RequestMapping(value = "/testRole3",method = RequestMethod.GET)
    @ResponseBody
    public String testRole3(){
        return "testRole3";
    }

}
