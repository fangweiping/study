package com.sgwl.filemanager.controller;


import com.alibaba.fastjson.JSON;
import com.sgwl.filemanager.service.FolderService;
import com.sgwl.filemanager.vo.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("folder")
public class FolderController {

    @Autowired
    private FolderService folderService;

    /**
     * 新建文件夹
     *
     * @param folderName
     * @return
     */
    @PostMapping("{pid}/{folderName}")
    public ResponseEntity<Void> createFoleder(@PathVariable Long pid, @PathVariable String folderName) {
        folderService.createFoleder(pid, folderName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 判断文件夹是否存在
     *
     * @return
     */
    @GetMapping("/{pid}/{folderName}")
    public ResponseEntity<RespResult> isExist(@PathVariable Long pid, @PathVariable String folderName) {
        return ResponseEntity.ok(folderService.isExist(pid, folderName));
    }


    /**
     * 获取zTreeNodes数据
     *
     * @return
     */
    @GetMapping("all")
    public String getAll() {
        LinkedList zTreeNodes = folderService.getZtreeNodes1();
        RespResult respResult = new RespResult<>(200, "查询成功", zTreeNodes);
        //为了保证数据结构顺序,使用FastJson进行序列化
        return JSON.toJSONString(respResult);
    }

    /**
     * 获取elementui treenodes数据
     *
     * @return
     */
    @GetMapping("treeNodes")
    public String getTreeNodes() {
        List treeNodes = folderService.getTreeNodes();
        RespResult respResult = new RespResult<>(200, "查询成功", treeNodes);
        //为了保证数据结构顺序,使用FastJson进行序列化
        return JSON.toJSONString(respResult);
    }

}
