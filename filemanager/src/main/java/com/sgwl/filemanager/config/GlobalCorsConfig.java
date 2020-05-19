package com.sgwl.filemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * 跨域配置
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        //1.添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1.1  允许的域,不要写*  不然cookie就无法使用
        config.addAllowedOrigin("http://localhost:63342");
        config.addAllowedOrigin("http://127.0.0.1:8020");
        config.addAllowedOrigin("http://localhost:8088");
        //1.2 是否发送Cookie信息
        config.setAllowCredentials(true);
        //1.3允许的请求方式
       /* config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("PATCH");*/
       config.addAllowedMethod("*");
        //1.4允许的头信息
        config.addAllowedHeader("*");
        //2 添加新的路径,我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        //3.返回新的CorsFilter
        return new CorsFilter(configSource);
    }
}
