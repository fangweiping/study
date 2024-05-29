package com.fwp.demo.dao;

import com.fwp.demo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> queryMenu();

}
