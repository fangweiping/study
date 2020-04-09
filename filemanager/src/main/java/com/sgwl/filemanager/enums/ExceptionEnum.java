package com.sgwl.filemanager.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    FILE_UPLOAD_FAIL(500, "文件上传失败!"),
    FILE_NOT_EXIST(400,"文件不存在!");

    private int value;
    private String msg;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
