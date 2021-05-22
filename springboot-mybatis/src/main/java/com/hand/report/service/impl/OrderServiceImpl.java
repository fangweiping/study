package com.hand.report.service.impl;

import com.hand.report.entity.Order;
import com.hand.report.dao.OrderMapper;
import com.hand.report.service.OrderService;
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
