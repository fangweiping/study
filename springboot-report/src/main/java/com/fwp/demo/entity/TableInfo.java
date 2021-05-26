package com.fwp.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 表信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableInfo {

    /**
     * 数据源id
     */
    private String dbId;

    /**
     *表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * 执行sql
     */
    @JsonIgnore
    private String executeSql;

}
