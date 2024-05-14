package com.fwp.demo.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSON;
import com.fwp.demo.aop.annotation.Login;
import com.fwp.demo.vo.RespResult;
import com.fwp.demo.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    DruidDataSource druidDataSource;

    @Autowired
    RedissonClient redissonClient;

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
    public User getMethod(User user) {
        return user;
    }

    @PostMapping("post")
    public User postMethod(@RequestBody User user) {
        return user;
    }

    @PostMapping("register")
    public User register(@RequestBody User user) {
        RLock lock = redissonClient.getLock("user_center_user_id_generate");


        try {
            //1.加锁
            lock.lock();
            DruidPooledConnection connection = druidDataSource.getConnection();
            Connection connection1 = connection.getConnection();
            //2.获取用户id最大值
            int userId = 1000001000;
            //3.id自增1000
            userId += 1000;
            //4.写入数据库
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
        } finally {
            //释放锁
            lock.unlock();
        }
        return user;
    }
}
