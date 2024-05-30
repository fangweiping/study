package com.fwp.study.controller;

import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author fwp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/05/24 16:45:00
 */
@RestController
@RequestMapping("cookie")
public class CookieSessionController {

    @GetMapping("getCookie")
    public Object getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        System.out.println("cookies = " + cookies);
        return cookies;
    }

    @GetMapping("setCookie")
    public Object setCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie1 = new Cookie("cookie1", "1234");
        cookie1.setHttpOnly(true);
        Cookie cookie2 = new Cookie("cookie2", "5566");
        cookie2.setHttpOnly(true);
        cookie2.setMaxAge(0);//cookie存活时间,不写就是会话级别的cookie,关闭浏览器就消失,写就是指定时间消失
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return response;
    }

    @GetMapping("getSession")
    public Object getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String jessionId = session.getId();
        Object name = session.getAttribute("name");
        return jessionId+":"+name;
    }

    @GetMapping("setSession")
    public Object setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name","张山");
        return null;
    }
}
