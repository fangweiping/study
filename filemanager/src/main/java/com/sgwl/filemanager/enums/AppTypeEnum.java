package com.sgwl.filemanager.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 应用类型枚举
 */
@NoArgsConstructor
@AllArgsConstructor
public enum AppTypeEnum {

    WDS_SHUIWEI(1,"水情水位"),

    WDS_JIANKONG(2,"水情监控");

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
