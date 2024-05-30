package com.fwp.study.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    String name;
    int age;


    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
