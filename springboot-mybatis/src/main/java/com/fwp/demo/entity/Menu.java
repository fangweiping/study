package com.fwp.demo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {

    private Integer id;

    private String name;

    private String url;

    private Integer pid;

    private Integer orderNum;

    private List<Menu> subList = new ArrayList<>();

}
