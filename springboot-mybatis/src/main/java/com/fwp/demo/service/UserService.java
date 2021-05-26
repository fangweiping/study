package com.fwp.demo.service;

import com.fwp.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void insert(User user);
}

