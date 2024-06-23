package com.fwp.study.filter;

import com.fwp.study.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
//@Component
public class AuthFilter extends ZuulFilter {

    /**
     * 过滤器类型
     * org.springframework.cloud.netflix.zuul.filters.support.FilterConstants 这个类
     * pre：在请求被路由（转发）之前调用
     * route：在路由（请求）转发时被调用
     * error：服务网关发生异常时被调用
     * post：在路由（转发）请求后调用
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器执行顺序，数字越大，优先级越低
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，此处为true，则会执行run方法
     */
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/auth/login") || requestURI.startsWith("/auth/logout")) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String accessToken = request.getHeader("access-token");
        String refreshToken = request.getHeader("refresh-token");

        if (accessToken == null || JwtUtils.parseToken(accessToken) == null) {
            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            currentContext.setResponseBody("认证失败");
            currentContext.getResponse().setContentType("application/json;charset=UTF-8");
            currentContext.setSendZuulResponse(false);
        }
        return null;
    }

    public static void main(String[] args) {
        Claims dlfjlsajfldlsd = JwtUtils.parseToken("dlfjlsajfldlsd");
    }
}
