package com.fwp.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fwp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/3/16 1:18 :00
 */
@Getter
@AllArgsConstructor
public enum ReturnCode {

    SUCCESS(200, "OK"),
    FAILED(500, "Failed");

    private final int code;
    private final String msg;
}
