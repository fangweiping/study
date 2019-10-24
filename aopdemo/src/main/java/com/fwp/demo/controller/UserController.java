package com.fwp.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fwp.demo.aop.annotation.Login;
import com.fwp.demo.vo.RespResult;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("get")
    public void getMethod(@RequestBody User user) {
        System.out.println(user);
    }


    @PostMapping("post")
    public void postMethod(@RequestBody User user) {
        System.out.println(user);
    }
}
