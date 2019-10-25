package com.sgwl.filemanager.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class FileVo {
    @JSONField(ordinal = 1)  //指定对象序列化以后的属性顺序
    private Long id;         //为满足zTreeNodes数据结构,给null即可
    @JSONField(ordinal = 2)
    private Long pId;
    @JSONField(ordinal = 3)
    private String name;
    @JSONField(ordinal = 4)
    private Long fileId;   //真实的文件ID

    public FileVo(Long id, Long pId, String name, Long fileId) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.fileId = fileId;
    }
}
