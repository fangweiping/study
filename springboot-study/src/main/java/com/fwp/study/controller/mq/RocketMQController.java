package com.fwp.study.controller.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketMQ")
public class RocketMQController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

//    @PostMapping("/name")
//    public void
}
