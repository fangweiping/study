package com.fwp.demo.service.impl;

import com.fwp.demo.entity.User;
import com.fwp.demo.dao.UserMapper;
import com.fwp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }
}
