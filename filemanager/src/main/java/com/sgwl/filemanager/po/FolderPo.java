package com.sgwl.filemanager.po;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "folder")
@Data
public class FolderPo {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name; //文件夹名称
    private Long pid;//父文件夹ID
    private Date createTime;//创建时间


}
