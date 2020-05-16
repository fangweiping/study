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

    @GetMapping("get1")
    public User get1( User user) {
        return user;
    }


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
