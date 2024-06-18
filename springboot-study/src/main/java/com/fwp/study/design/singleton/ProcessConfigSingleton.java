package com.fwp.study.design.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程配置信息单例
 */
public class ProcessConfigSingleton {

    private static ProcessConfigSingleton processConfigSingleton;

    private Map<String, ProcessConfig> processConfigMap;

    private ProcessConfigSingleton() {
        //查询数据库获取流程配置信息
        processConfigMap = new HashMap<String, ProcessConfig>();
        processConfigMap.put("processKey", new ProcessConfig());
    }

    public static ProcessConfigSingleton getInstance() {
        if (processConfigSingleton == null) {
            synchronized (ProcessConfigSingleton.class) {
                if (processConfigSingleton == null) {
                    processConfigSingleton = new ProcessConfigSingleton();
                }
            }
        }
        return processConfigSingleton;
    }
}
