package com.fwp.demo.service.impl;

import com.fwp.demo.entity.User;
import com.fwp.demo.repository.UserMapper;
import com.fwp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
