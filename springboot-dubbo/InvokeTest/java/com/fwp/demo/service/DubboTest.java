package com.fwp.demo.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

public class
DubboTest {
    public static void main(String[] args) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("dubbo-consumer1"));
        reference.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
        reference.setTimeout(20000);
        reference.setInterface("com.fwp.demo.service.ProviderService");
        reference.setVersion("1.0.0");
        reference.setGeneric(true);
        reference.setCheck(false);
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);
        JSONObject jsonObject = JSON.parseObject("{\"id\": 1,\"user\": {\"age\": 1,\"username\": \"lis\"}}");

        Object method = genericService.$invoke("testJson", new String[]{"java.util.Map"}, new Object[]{jsonObject});
        String className = method.getClass().getName();
        System.out.println("className = " + className);
        System.out.println("1111111111111111111111111111111111111111111"+JSON.toJSONString(method));

    }
}
