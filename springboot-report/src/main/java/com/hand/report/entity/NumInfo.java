package com.hand.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取号
 * @author dwl
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
