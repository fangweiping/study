package com.fwp.study.design.singleton;

import lombok.Data;

@Data
public class ProcessConfig {

    /**
     * 流程key值
     */
    private String processKey;

    /**
     * 流程名称
     */
    private String processName;

}
