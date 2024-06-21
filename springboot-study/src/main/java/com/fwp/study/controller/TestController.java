package com.fwp.study.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.fwp.study.config.nosql.redis.RedisConfig;
import com.fwp.study.context.UserContext;
import com.fwp.study.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private DruidDataSource druidDataSource;

    @GetMapping("1")
    public void test1() throws SQLException {
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println("connection = " + connection);
        System.out.println(UserContext.getUser());
    }

}
