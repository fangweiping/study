package com.fwp.study.design.singleton;

import java.util.Map;

/**
 * 流程配置信息单例
 */
public class ProcessConfigSingleton {

    private static ProcessConfigSingleton processConfigSingleton;

    private Map<String, ProcessConfig> processConfigMap;

    private ProcessConfigSingleton() {
        //查询数据库获取流程配置信息
        processConfigMap.put("processKey", new ProcessConfig());
    }

    public static ProcessConfigSingleton getInstance() {
        if (processConfigSingleton == null) {
            synchronized (ProcessConfigSingleton.class) {
                if (processConfigSingleton == null) {
                    new ProcessConfigSingleton();
                }
            }
        }
        return processConfigSingleton;
    }
}
