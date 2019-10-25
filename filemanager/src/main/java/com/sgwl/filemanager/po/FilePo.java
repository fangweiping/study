package com.sgwl.filemanager.po;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "file")
@Data
public class FilePo {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;    //文件ID
    private String name;// 文件名称
    @JsonIgnore
    private Long pid;//所属文件夹ID
    @JsonIgnore
    private Long size;//文件夹大小,单位Byte
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime; //更新时间

}
