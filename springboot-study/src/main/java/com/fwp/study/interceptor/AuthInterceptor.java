package com.fwp.study.interceptor;

import com.fwp.study.context.UserContext;
import com.fwp.study.utils.JwtUtils;
import com.fwp.study.vo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取token
        String token = request.getHeader("Authorization");
        //2.校验token
        String str = JwtUtils.parseToken(token);
        if (token == null || str == null) {
            response.setStatus(401);
            //return false  postHandle   afterCompletion  不会再执行
//            return false;
        }
        //3.从token中获取用户信息,并用ThreadLocal存储
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        UserContext.setUser(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        //避免内存溢出,手动清空threadLocal
    }
}
