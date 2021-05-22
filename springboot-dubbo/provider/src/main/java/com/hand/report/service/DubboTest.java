package com.hand.report.service;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

public class DubboTest {
    public static void main(String[] args) {
        ReferenceConfig<GenericService> refrence = new ReferenceConfig<>();
        refrence.setApplication(new ApplicationConfig("dubbo-provider"));
        refrence.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
        refrence.setTimeout(20000);
        refrence.setInterface("com.hand.report.service.ProviderService");
        //refrence.setGroup("mm");
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(refrence);
        Object method = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"hello"});
        System.out.println(JSON.toJSONString(method));

    }
}
