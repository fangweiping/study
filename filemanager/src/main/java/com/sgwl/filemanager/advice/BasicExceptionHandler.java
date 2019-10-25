package com.sgwl.filemanager.advice;

import com.sgwl.filemanager.exception.FileManagerException;
import com.sgwl.filemanager.vo.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 自定义异常统一处理
 */
@ControllerAdvice
@Slf4j
public class BasicExceptionHandler {

    @ExceptionHandler(FileManagerException.class)//捕获指定自定义异常
    //被声明的方法可以看作是一个SpringMVC的handler
    public ResponseEntity<ExceptionResult> handlerException(FileManagerException e) {
        //从异常中获取友好信息
        return ResponseEntity.status(e.getExceptionEnum().value()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
