package com.fwp.study.design.strategy;

public class ClientTest {

    public static void main(String[] args) {
        AliPay aliPay = new AliPay();
        PayContext payContext = new PayContext(aliPay);
        PayParam payParam = new PayParam();
        payContext.executePayStrategy(payParam);
    }
}
