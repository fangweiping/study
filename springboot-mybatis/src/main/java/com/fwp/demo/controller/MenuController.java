package com.fwp.demo.controller;

import com.fwp.demo.entity.Menu;
import com.fwp.demo.service.impl.   MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    /**
     *
         CREATE TABLE `t_menu` (
         `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
         `name` varchar(255) NOT NULL COMMENT '菜单名称',
         `url` varchar(255) DEFAULT NULL COMMENT '菜单链接',
         `pid` int DEFAULT NULL COMMENT '父菜单id',
         `order_num` int NOT NULL COMMENT '排序字段',
         PRIMARY KEY (`id`),
         UNIQUE KEY `uniq_url` (`url`) USING BTREE
         ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     *`
     *
     */
    @Autowired
    private MenuService menuService;

    @GetMapping("/queryMenuTree")
    public List<Menu> queryMenuTree() {
        return menuService.queryMenuTree();
    }

}
