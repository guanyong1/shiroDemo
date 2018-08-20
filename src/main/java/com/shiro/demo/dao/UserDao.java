package com.shiro.demo.dao;

import com.shiro.demo.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 10:46 2018/4/11
 * @Modified By:
 */
public interface UserDao {

    UserBean getUserByPwd(@Param("pwd") String pwd);

    UserBean getUserByUserName(@Param("username") String userName);

    List<String> getRolesByUserName(@Param("username") String username);
}
