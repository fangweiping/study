package com.fwp.demo.service;

import java.util.Map;

public interface ProviderService {
    String sayHello(String name);


    /**
     * 测试使用map接受嵌套json参数
     * @param params
     * @return
     */
    String testJson(Map<String,Object> params);
}
