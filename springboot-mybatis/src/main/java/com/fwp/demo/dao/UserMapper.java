package com.fwp.demo.dao;

import com.fwp.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    User queryUserByUserName(String username);
}
