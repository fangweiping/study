package com.hand.report.entity;

import lombok.Data;

/**
 * 取号
 * @author dwl
 *
 */
@Data
public class NumInfo {
    /**
     * 主键
     */
    private String numId;

    /**
     * 前缀
     */
    private String numPre;

    /**
     * 补位
     */
    private Integer numLen;

    /**
     * 当前序号
     */
    private Integer numNow;
}
