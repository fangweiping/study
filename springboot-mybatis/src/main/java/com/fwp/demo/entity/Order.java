package com.fwp.demo.entity;


import lombok.Data;

/**
 * 订单表
 *
 */
@Data
public class Order {

    private Integer id;

    private Long userId;

    private String orderNumber;

    private User user;


	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", orderNumber="
				+ orderNumber + "]";
	}

}
