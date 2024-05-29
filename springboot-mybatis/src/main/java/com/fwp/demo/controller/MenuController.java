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

    @Autowired
    private MenuService menuService;

    @GetMapping("/queryMenuTree")
    public List<Menu> queryMenuTree() {
        return menuService.queryMenuTree();
    }

}
