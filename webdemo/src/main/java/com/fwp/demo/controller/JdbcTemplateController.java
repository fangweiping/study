package com.fwp.demo.controller;/**
 * @author Fang WeiPing
 * @date 2020/5/19 16:58
 */

import com.fwp.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/19 16:58 
 */
@RestController
public class JdbcTemplateController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/jdbc")
    public User jdbc(@RequestBody User user) {
        jdbcTemplate.execute("select * from goodsInfo limit 1 ");


        return  user;
    }

}
