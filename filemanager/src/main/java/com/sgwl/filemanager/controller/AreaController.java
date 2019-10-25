package com.sgwl.filemanager.controller;

import com.sgwl.filemanager.service.AreaService;
import com.sgwl.filemanager.vo.AreaNode;
import com.sgwl.filemanager.vo.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("all")
    public ResponseEntity<RespResult<AreaNode>> getTreeNode() {
        List treeNode = areaService.getTreeNode();
        RespResult resp = new RespResult<>(200, "查询成功", treeNode);
        return ResponseEntity.ok(resp);
    }
}
