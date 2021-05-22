package com.hand.report.entity;

import lombok.Data;

/**
 * 商品表
 */
@Data
public class Item {

    private Integer id;

    private String itemName;

    private Float itemPrice;

    private String itemDetail;


	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", itemPrice="
				+ itemPrice + ", itemDetail=" + itemDetail + "]";
	}

}
