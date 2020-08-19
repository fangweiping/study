package com.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consumer   {

    @Reference(version = "1.0.0",timeout = 300)
    private ProviderService service;

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable String name){
        return service.sayHello(name);
    }
}
