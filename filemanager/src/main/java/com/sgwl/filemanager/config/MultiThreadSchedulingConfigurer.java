package com.sgwl.filemanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * 定时任务线程池配置类
 */
@Configuration
public class MultiThreadSchedulingConfigurer implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        // 修改初始化线程数为100
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(100));
    }
}
