package com.fwp.study.reptile.utils;/**
 * @author Fang WeiPing
 * @date 2020/6/5 10:50
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/6/5 10:50
 */
@Data
public class AgentResponse implements Serializable {

    @JsonProperty("code")
    String code;
    @JsonProperty("msg")
    public
    List<AgentInfo> msg;
}
