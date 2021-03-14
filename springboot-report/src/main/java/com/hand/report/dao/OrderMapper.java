package com.hand.report.dao;


import com.hand.report.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Order queryOrderDetail(Long orderNum);
}
