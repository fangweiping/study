package com.fwp.demo.service.impl;

import com.fwp.demo.dao.MenuMapper;
import com.fwp.demo.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> queryMenuTree() {
        List<Menu> menuList = menuMapper.queryMenu();
        return buildMenuTree(menuList);
    }

    public List<Menu> buildMenuTree(List<Menu> menuList) {
        List<Menu> rootList = menuList.stream()
                .filter(menu -> Objects.isNull(menu.getPid())).collect(Collectors.toList());
        for (Menu menu : rootList) {
            getSubList(menu, menuList);
        }
        return rootList;
    }

    public void getSubList(Menu menu, List<Menu> menuList) {
        for (Menu m : menuList) {
            if (menu.getId().equals(m.getPid())) {
                menu.getSubList().add(m);
                getSubList(m , menuList);
            }
        }
    }

}
