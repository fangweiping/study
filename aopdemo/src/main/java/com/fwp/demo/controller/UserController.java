package com.fwp.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fwp.demo.aop.annotation.Login;
import com.fwp.demo.vo.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("login")
    @Login("开始登陆")
    public String doLogin() {
        RespResult resp = new RespResult();
        resp.setCode(200);
        resp.setMsg("登陆成功");
        resp.setData(null);
        return JSON.toJSONString(resp);
    }
}
