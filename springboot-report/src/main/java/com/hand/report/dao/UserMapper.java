package com.hand.report.dao;

import com.hand.report.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    int insert(User user);
}
