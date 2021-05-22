package com.hand.report.controller;

import com.hand.report.service.ProviderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consumer   {

    @Reference(version = "1.0.0",timeout = 300,check = false)
    private ProviderService service;

    @GetMapping(value = "/hello/{name}") //http://localhost:8081/hello/nihao
    public String hello(@PathVariable String name){
        return service.sayHello(name);
    }
}
