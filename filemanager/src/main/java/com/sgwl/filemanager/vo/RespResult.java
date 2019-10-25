package com.sgwl.filemanager.vo;

import lombok.Data;

@Data
public class RespResult<T> {
    private int status;
    private String msg;
    private boolean isExist;
    private T data;


    public RespResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public RespResult(int status, String msg, boolean isExist) {
        this.status = status;
        this.msg = msg;
        this.isExist = isExist;
    }
}
