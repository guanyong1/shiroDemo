package com.shiro.demo.service;

import com.shiro.demo.bean.UserBean;

import java.util.List;

public interface IUserService {

    UserBean getUserByPwd(String pwd);

    UserBean getUserByUserName(String userName);

    List<String> getRolesByUserName(String userName);
}
