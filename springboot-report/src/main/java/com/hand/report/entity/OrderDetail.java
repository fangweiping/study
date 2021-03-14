package com.hand.report.entity;

import lombok.Data;

@Data
public class OrderDetail {

    private Integer id;

    private Double totalPrice;

    private Integer status;

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", totalPrice=" + totalPrice
				+ ", status=" + status + "]";
	}

}
