package com.fwp.study.service.mq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RocketMQService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步消息
     * 发送同步消息是指producer向 broker发送消息,执行API时同步等待,直到broker服务器返回发送结果
     */
    public void syncSend() {
        SendResult sendResult = rocketMQTemplate.syncSend("topic", MessageBuilder.withPayload("Hello RocketMQ 同步消息"));
        System.out.println(JSON.toJSON(sendResult));
    }

    /**
     * 异步消息
     * 指producer向broker发送消息时异步执行，不会影响后面逻辑。而异步里面会调用一个回调方法，来处理消息发送成功或失败的逻辑
     */
    public void asyncSend() {
        rocketMQTemplate.asyncSend("topic", "Hello RocketMQ 异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步消息 发送成功！");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("异步消息 发送失败！");
            }
        });
    }

    /**
     * 单向消息
     * 是指producer向 broker发送消息,执行API时直接返回，不等待broker 服务器的响应
     */
    public void sendOneWay() {
        rocketMQTemplate.sendOneWay("topic", "Hello RocketMQ 单项消息");
    }
}
