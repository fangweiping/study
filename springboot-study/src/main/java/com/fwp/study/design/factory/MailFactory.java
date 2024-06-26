package com.fwp.study.design.factory;

/**
 * 邮箱发送工厂类
 */
public class MailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
