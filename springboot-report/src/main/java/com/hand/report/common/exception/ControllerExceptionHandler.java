package com.hand.report.common.exception;

import com.hand.report.common.util.ResultFactory;
import com.hand.report.common.util.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @author fwp
 * @version 1.0.0
 * @Description 统一异常处理
 * @createTime 2021/3/16 1:17:00
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultObject handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultFactory.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultObject handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResultFactory.error(e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResultObject handleSQLException(SQLException e) {
        log.error(e.getMessage(), e);
        return ResultFactory.error("sql执行异常:"+e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public ResultObject handleServiceException(ServiceException e) {
        log.error(e.getMessage(), e.getCode());
        return ResultFactory.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResultObject handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResultFactory.error(message);
    }

}
