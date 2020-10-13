package com.chenhao.stuscore.conponent;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录页面拦截器,登录检查，如果没登录  不能登录主页
 * @author ch
 * @date 2020/8/30
 **/
public class LoginHandlerIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user == null){
            //未登录,返回登录页面
            request.setAttribute("msg","没有权限请先登录");
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }else {
            //已登录，放行请求
            return true;
        }



//            return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
