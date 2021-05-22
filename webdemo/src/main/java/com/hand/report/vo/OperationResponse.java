package com.hand.report.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OperationResponse implements Serializable {
    private  String code;
    private String success;
    private List<OperationVO> data;
    private String msg;
}
