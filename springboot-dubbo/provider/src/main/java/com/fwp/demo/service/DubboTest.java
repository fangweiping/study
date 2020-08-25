package com.fwp.demo.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

public class DubboTest {
    public static void main(String[] args) {
        ReferenceConfig<GenericService> refrence = new ReferenceConfig<>();
        refrence.setApplication(new ApplicationConfig("test"));
        refrence.setRegistry(new RegistryConfig("zookeeper://160.5.29.24:2181"));
        refrence.setGeneric(true);
        refrence.setTimeout(20000);
        refrence.setInterface("com.");
        refrence.setGroup("mm");
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(refrence);
        Object method = genericService.$invoke("method", new String[]{}, new Object[]{10000});
        System.out.println(JSON.toJSONString(method));

    }
}
