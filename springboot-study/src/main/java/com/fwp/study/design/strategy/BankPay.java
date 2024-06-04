package com.fwp.study.design.strategy;

public class BankPay implements PayStrategy {

    @Override
    public PayResult pay(PayParam payParam) {
        System.out.println("银行支付");
        return null;
    }
}
