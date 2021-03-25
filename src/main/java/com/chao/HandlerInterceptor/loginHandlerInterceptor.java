package com.chao.HandlerInterceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拿到用户session
        Object loginUser = request.getSession().getAttribute("user");

        //用户没有登入的话，跳转到登入界面
        if (loginUser==null)
        {
            request.setAttribute("massage","没有权限,请先登入！");
            request.getRequestDispatcher("/admin").forward(request,response);
            return false;
        }

        return true;
    }

}
