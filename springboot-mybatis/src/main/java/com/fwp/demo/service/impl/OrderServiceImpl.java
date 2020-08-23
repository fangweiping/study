package com.fwp.demo.service.impl;

import com.fwp.demo.entity.Order;
import com.fwp.demo.repository.OrderMapper;
import com.fwp.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order queryOrderDetail(Long orderNum) {
        return orderMapper.queryOrderDetail(orderNum);
    }

}
