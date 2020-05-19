package com.fwp.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fwp.demo.vo.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fang WeiPing
 * @date 2020/5/8 16:50
 *
 * springmvc 注解使用demo
 */
@RestController
@RequestMapping("springmvc")
public class SpringmvcAnotationDemoController {


    /**
     * 不适用任何注解的情况下
     * 使用mvc框架会从url参数中获取参数绑定到实体中
     * @param user
     * @return
     */
    @GetMapping("get1")
    public User get1( User user) {
        return user;
    }


    /**
     * 报错  get没有请求体
     * @param user
     * @return
     */
    @GetMapping("get2")
    public User get2( @RequestBody User user) {
        return user;
    }

    @PostMapping("post1")
    public User  post1(@RequestBody User user) {
        return user;
    }

    @PostMapping("post2")
    public String  post2(@RequestBody String userString) {
        return userString;
    }

    @PostMapping("post3")
    public User  post3(User user) {
            return user;
    }


    public static void main(String[] args) {
        String json = JSON.toJSONString(new User("fwp", 25));
        System.out.println("json = " + json);
    }
}
