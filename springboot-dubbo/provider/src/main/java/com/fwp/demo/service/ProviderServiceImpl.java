package com.fwp.demo.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.fwp.demo.pojo.User;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 添加@Service注解，切记注解不是用spring的service注解，是  org.apache.dubbo.config.annotation.Service;
 * 由阿里提供的dubbo的注解，配上版本号1.0.0说明向zookeeper注册的是版本为1.0.0的TestService接口，超时时长为3000ms等信息。
 */
@Service(version = "1.0.0", timeout = 3000)
@Component
public class ProviderServiceImpl implements ProviderService {
    @Override
    public String sayHello(String name) {
        return name;
    }

    @Override
    public String testJson(Map<String, Object> params) {
        Object id = params.get("id");
        System.out.println("id = " + id);
        Object user = params.get("user");
        System.out.println("user = " + user);
        user = JSON.toJavaObject(JSON.parseObject(user.toString()), User.class);
        System.out.println("user = " + user);


        return "调用成功";
    }
}
