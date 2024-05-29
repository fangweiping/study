package com.fwp.demo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {

    private Long id;

    private String name;

    private String link;

    private Long parentId;

    private Long orderNum;

    private List<Menu> subList = new ArrayList<>();

}
