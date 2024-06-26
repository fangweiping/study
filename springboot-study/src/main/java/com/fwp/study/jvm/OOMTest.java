package com.fwp.study.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OOMTest {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        try {
            es.execute(() -> {
                List<Object> list = new ArrayList<Object>();
                while (true) {
                    list.add(new boolean[1024 * 1024]);
                }
            });
        } catch (Exception e) {
        } finally {
            System.out.println("你好啊");

        }
    }
}
