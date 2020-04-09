package com.sgwl.filemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 线程池初始化
 */
@Configuration
public class InitTreadPool {

    /**
     * spring提供的定时任务线程池
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setAwaitTerminationSeconds(60);
        //任务执行完才会关机
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
