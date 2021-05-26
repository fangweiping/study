package com.fwp.demo.dao;


import com.fwp.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Order queryOrderDetail(Long orderNum);
}
