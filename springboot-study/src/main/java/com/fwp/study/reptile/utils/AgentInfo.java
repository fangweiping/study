package com.fwp.study.reptile.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public
class AgentInfo implements Serializable {
    @JsonProperty("port")
    String port;
    @JsonProperty("ip")
    String ip;
}
