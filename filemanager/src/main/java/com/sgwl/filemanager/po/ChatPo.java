package com.sgwl.filemanager.po;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Table;

@Data
@Table(name = "chat_detail")
public class ChatPo {
    @KeySql(useGeneratedKeys = true)
    private String id;
    private String time;
    private String name;
    private String content;
}
