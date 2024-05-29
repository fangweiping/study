package com.fwp.demo.controller;

import com.fwp.demo.entity.ServiceView;
import com.fwp.demo.service.ServiceViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/serviceView")
public class ServiceViewController {

    @Autowired
    private ServiceViewService serviceViewService;

    @PostMapping("/update")
    public void update(@RequestBody ServiceView serviceView) {
        serviceViewService.update(serviceView);
    }
}
