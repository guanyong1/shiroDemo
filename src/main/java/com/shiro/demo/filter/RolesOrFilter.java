package com.shiro.demo.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author:guang yong
 * Description:自定义授权filter,只要满足其中一个角色即可访问
 * @Date:Created in 11:24 2018/8/21
 * @Modified By:
 */
public class RolesOrFilter extends AuthorizationFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        //获取主体
        Subject subject = getSubject(servletRequest,servletResponse);
        String[] roles = (String[]) o;
        if(roles == null || roles.length == 0){
            return true;
        }
        for (String role:roles) {
            if (subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }
}
