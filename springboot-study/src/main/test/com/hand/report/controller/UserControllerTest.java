package com.hand.report.controller;

import com.hand.report.ApplicationDemo;
import com.hand.report.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试使用步骤:
 * 1.测试类路径要和java下的保持一致
 * 2.指定运行器
 * 3.指定启动类
 * 4.@Test声明测试方法
 */
@RunWith(SpringRunner.class)  //指定运行器
@SpringBootTest(classes = ApplicationDemo.class) //指定启动类
public class UserControllerTest{

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test //使用@Test注解时不要导错包
    public void doLogin() {
        System.out.println("redisConfig = " + redisConfig);
        System.out.println("redisTemplate = " + redisTemplate);
    }
}
