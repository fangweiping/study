package com.fwp.demo.vo;

import lombok.Data;

@Data
public class RespResult {
    private int code;
    private String msg;
    private Object data;

}
