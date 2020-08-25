package com.fwp.demo.controller;

import com.fwp.demo.service.ProviderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
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
