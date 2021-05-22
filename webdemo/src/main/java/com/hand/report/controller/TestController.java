package com.hand.report.controller;

import com.hand.report.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private RedisConfig redisConfig;

    @GetMapping("1")
    public void test1() {
        System.out.println("redisConfig = " + redisConfig);
    }

}
