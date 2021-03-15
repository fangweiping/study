package com.hand.report.common.exception;

import com.hand.report.common.enums.ReturnCode;
import lombok.Getter;

@Getter
public class ServiceException extends  RuntimeException {
    private int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ReturnCode returnCode) {
        super(returnCode.getMsg());
        this.code = returnCode.getCode();
    }
}
