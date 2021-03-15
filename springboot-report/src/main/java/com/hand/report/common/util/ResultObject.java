package com.hand.report.common.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回结果对象
 * @param <T>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultObject<T> implements Serializable {

    private int code;//状态码
    private Boolean success;//是否成功
    private String msg;//描述
    private Long timeStamp;//时间戳
    private T data;//返回数据体

}
