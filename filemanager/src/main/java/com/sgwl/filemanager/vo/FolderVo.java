package com.sgwl.filemanager.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class FolderVo {
    @JSONField(ordinal=1)  //指定对象序列化以后的属性顺序
    private Long id;
    @JSONField(ordinal=2)
    private Long pId;
    @JSONField(ordinal=3)
    private String name;

    public FolderVo(Long id, Long pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }
}
