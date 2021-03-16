package com.hand.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fwp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/3/16 9:57:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseCount {

    private String name;//名称
    private String num;//数量

}
