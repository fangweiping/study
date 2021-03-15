package com.hand.report.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCode {

    SUCCESS(200, "OK"),
    FAILED(500, "Failed");

    private final int code;
    private final String msg;
}
