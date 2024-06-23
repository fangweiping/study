//package com.fwp.study.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//@Slf4j
//public class LoginFilter extends ZuulFilter {
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    @SuppressWarnings("all")
//    public Object run()  {
//        RequestContext context = RequestContext.getCurrentContext();
//        context.setSendZuulResponse(true);
//        Throwable throwable = context.getThrowable();
//        log.error("this is a ErrorFilter :" + throwable.getCause().getMessage(), throwable);
//        context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        context.set("error.exception", throwable.getCause());
//        return null;
//    }
//}
