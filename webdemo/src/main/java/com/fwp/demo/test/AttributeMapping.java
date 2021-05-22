package com.fwp.demo.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(SPU)_属性映射关系表
 * @author Neo
 * @since 2021/4/20 13:27
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自定义属性名
     */
    private String customizeKey;

    /**
     * 自定义属性值
     */
    private String customizeValue;

    /**
     * SKU 属性
     */
    private List<AttributeMapping> attributeList;
}