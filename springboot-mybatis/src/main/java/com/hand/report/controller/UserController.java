package com.hand.report.controller;

import com.hand.report.entity.User;
import com.hand.report.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("findAll") //http://localhost:8080/user/findAll
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("insert")//http://localhost:8080/user/insert
    public User insert(User user) {
        //User user = new User();
        //user.setDeleteFlag(true);
        userService.insert(user);
        return user;
    }

}
