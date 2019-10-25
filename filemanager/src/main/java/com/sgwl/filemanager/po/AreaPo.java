package com.sgwl.filemanager.po;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "base_area")
public class AreaPo {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long codeid;
    private Long parentid;
    @Column(name = "cityName")
    private String cityName;

}
