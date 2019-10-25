package com.sgwl.filemanager.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    FILE_UPLOAD_FAIL(500, "文件上传失败!"),
    FILE_NOT_EXIST(400,"文件不存在!");

    private int value;
    private String msg;

    public int value() {
        return value;
    }

    public String msg() {
        return msg;
    }
}
