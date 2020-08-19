package com.fwp.demo.controller;

import com.fwp.demo.entity.User;
import com.fwp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/findAll") //http://localhost:8080/user/findAll
    public List<User> findAll() {
        return userService.findAll();
    }


}
