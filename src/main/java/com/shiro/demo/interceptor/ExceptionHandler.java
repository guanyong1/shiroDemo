package com.shiro.demo.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;

/**
 * 异常处理类
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        log.error(DateFormat.getDateTimeInstance().format(new Date()) + "异常信息", ex);
        ModelAndView modelAndView = new ModelAndView("WEB-INF/404.jsp");
        modelAndView.addObject("msg", ex);
        return modelAndView;
    }
}
