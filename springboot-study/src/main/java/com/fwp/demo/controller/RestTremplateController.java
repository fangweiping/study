package com.fwp.demo.controller;

import com.fwp.demo.vo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 使用示例
 */
@RestController
@RequestMapping("rest")
public class RestTremplateController {


    private RestTemplate restTemplate  = new RestTemplate();

    @GetMapping("post")
    public User post() {
        User ss = new User("ss", 1);
        User user = restTemplate.postForObject("http://localhost:8080/user/post",ss, User.class);
        return user;
    }




}
