package com.fwp.study.reptile.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
class AgentInfo implements Serializable {
    @JsonProperty("port")
    String port;
    @JsonProperty("ip")
    String ip;
}
