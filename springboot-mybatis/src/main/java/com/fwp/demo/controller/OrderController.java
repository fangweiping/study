package com.fwp.demo.controller;

import com.fwp.demo.entity.Order;
import com.fwp.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/detail/{orderNum}")//http://localhost:8080/order/detail/20140921001
    public Order queryOrderDetail(@PathVariable Long orderNum) {
      return   orderService.queryOrderDetail(orderNum);
    }
}
