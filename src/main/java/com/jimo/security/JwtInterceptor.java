package com.jimo.security;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jimo
 * @func 拦截token并验证，不通过则抛出异常
 * @date 2018/8/24 22:38
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("prehandle");
        final String authorization = request.getParameter("Authorization");
        /*String authHeader = request.getHeader("Authorization");*/
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new ServletException("invalid Authorization header,请重新登陆");
        }
        //取得token
        String token = authorization.substring(7);
        try {
            JwtUtil.checkToken(token);
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
