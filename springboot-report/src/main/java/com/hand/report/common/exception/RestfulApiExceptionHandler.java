/*
package com.hand.report.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestfulApiExceptionHandler {
    */
/**
     * 缺失参数
     * @param request
     * @param exception
     * @return
     *//*

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Response missingParameterExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException exception){
        return Response.failure("缺少必要参数,参数名称为" + exception.getParameterName());
    }

    */
/**
     * 参数类型不匹配
     * @param request
     * @param exception
     * @return
     *//*

    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public Response typeMismatchExceptionHandler(HttpServletRequest request,TypeMismatchException exception){
        return Response.failure("参数类型不匹配,类型应该为" + exception.getRequiredType());
    }

    */
/**
     * 请求方法不支持
     * @param request
     * @param exception
     * @return
     *//*

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Response methodNotSupportedExceptionHandler(HttpServletRequest request,HttpRequestMethodNotSupportedException exception){
        return Response.failure("不支持的请求方法");
    }
    */
/**
     * 其他异常
     * @param request
     * @param exception
     * @return
     *//*

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest request, Exception exception){
        return Response.failure("系统异常");
    }
}
*/
