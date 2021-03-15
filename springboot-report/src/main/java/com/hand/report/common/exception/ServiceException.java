package com.hand.report.common.exception;

import com.hand.report.common.enums.ReturnCode;
import lombok.Getter;

/**
 * @author fwp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/3/16 1:18 :00
 */
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
