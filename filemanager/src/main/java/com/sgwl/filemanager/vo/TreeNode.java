package com.sgwl.filemanager.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class TreeNode {
    @JSONField(ordinal = 1)  //指定对象序列化以后的属性顺序
    private Long id;  //节点ID
    @JSONField(ordinal = 2)
    private Long pid;  //父节点ID
    @JSONField(ordinal = 3)
    private String label; //名称
    @JSONField(ordinal = 4)
    private boolean parentNodeFlag;//是否为父节点
    @JSONField(ordinal = 5)
    private List<TreeNode> children; //子节点对象集合

    public TreeNode(Long id, Long pid, String name, boolean parentNodeFlag, List<TreeNode> children) {
        this.id = id;
        this.pid = pid;
        this.label = name;
        this.parentNodeFlag = parentNodeFlag;
        this.children = children;
    }
}
