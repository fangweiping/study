package com.fwp.demo.dao;

import com.fwp.demo.entity.User;
import com.fwp.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    int insert(User user);
}
