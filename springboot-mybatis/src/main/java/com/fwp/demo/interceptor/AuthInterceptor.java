package com.fwp.demo.interceptor;

import com.fwp.demo.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || JwtUtils.parseToken(token) == null) {
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
