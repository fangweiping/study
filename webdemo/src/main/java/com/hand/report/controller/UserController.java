package com.hand.report.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.hand.report.aop.annotation.Login;
import com.hand.report.vo.RespResult;
import com.hand.report.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    DruidDataSource druidDataSource;

    @GetMapping("login")
    //@Login(value = "开始登陆")
    public String doLogin() {
        RespResult resp = new RespResult();
        resp.setCode(200);
        resp.setMsg("登陆成功");
        resp.setData(null);
        //System.out.println("druidDataSource = " + druidDataSource);
        //System.out.println(druidDataSource.getMaxIdle());
        Object currentProxy = AopContext.currentProxy();
        System.out.println("currentProxy = " + currentProxy);
        UserController proxy = (UserController) currentProxy;
        User method = proxy.getMethod(new User());
        return JSON.toJSONString(resp);
    }


    @GetMapping("get")
    @Login()
    public User getMethod( User user) {
        return user;
    }

    @PostMapping("post")
    public User postMethod(@RequestBody User user) {
        return user;
    }
}
