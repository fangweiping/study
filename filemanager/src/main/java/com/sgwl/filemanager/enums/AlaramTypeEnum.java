package com.sgwl.filemanager.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 告警类型枚举
 */
@NoArgsConstructor
@AllArgsConstructor
public enum  AlaramTypeEnum {

    YUEJIE(1,"越界"),

    BIANFU(2,"变幅");

    private int code;

    private String name;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
