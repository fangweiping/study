package com.fwp.study.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * SKU信息
 * @author Neo
 * @since 2021/4/20 13:28
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sku implements Serializable {

    private static final long serialVersionUID = 1792813905402371843L;

    /**
     * sku编码：MD5(sku_data)
     */
    private String skuCode;

    /**
     * SKU 属性
     */
    private List<AttributeMapping> attributeList;

}