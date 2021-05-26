package com.fwp.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fwp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/04/16/ 17:01:00
 */
@Configuration
public class DruidDataSourceConfig {

    @Bean
    public DruidDataSource getDruidDataSource() {
        System.out.println("DruidDataSource初始化");
        return new DruidDataSource();
    }
}
