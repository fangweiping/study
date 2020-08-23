package com.fwp.demo.repository;


import com.fwp.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Order queryOrderDetail(Long orderNum);
}
