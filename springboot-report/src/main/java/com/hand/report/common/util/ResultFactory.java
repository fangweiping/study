package com.hand.report.common.util;

import java.io.Serializable;

/**
 * 返回工具类
 */
public class ResultFactory<T> implements Serializable {


    /**
     * 返回成功
     *
     * @param <T>
     * @return
     */
    public static <T> ResultObject<T> success() {
        return new ResultObject<>(200, true, "", System.currentTimeMillis(), null);
    }


    /**
     * 返回成功
     *
     * @param <T>
     * @return
     */
    public static <T> ResultObject<T> success(String msg) {
        return new ResultObject<>(200, true, msg, System.currentTimeMillis(), null);
    }


    /**
     * 返回成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultObject<T> success(T data) {
        return new ResultObject<>(200, true, "", System.currentTimeMillis(), data);
    }

    /**
     * 返回成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultObject<T> success(String msg, T data) {
        return new ResultObject<>(200, true, msg, System.currentTimeMillis(), data);
    }

    /**
     * 返回异常
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultObject<T> error(int code, String msg, T data) {
        return new ResultObject<>(code, false, msg, System.currentTimeMillis(), data);
    }

    /**
     * 返回异常
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultObject<T> error(int code, String msg) {
        return new ResultObject<>(code, false, msg, System.currentTimeMillis(), null);
    }

    /**
     * 返回异常
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultObject<T> error(String msg) {
        return new ResultObject<>(400, false, msg, System.currentTimeMillis(), null);
    }

    /**
     * 返回异常
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultObject<T> error(String msg, T data) {
        return new ResultObject<>(400, false, msg, System.currentTimeMillis(), data);
    }
}
