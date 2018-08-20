package com.shiro.demo.service.impl;

import com.shiro.demo.bean.UserBean;
import com.shiro.demo.dao.UserDao;
import com.shiro.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    public UserBean getUserByPwd(String pwd) {
        return userDao.getUserByPwd(pwd);
    }

    @Override
    public UserBean getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public List<String> getRolesByUserName(String userName) {
        return userDao.getRolesByUserName(userName);
    }
}
