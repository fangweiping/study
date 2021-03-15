package com.hand.report.entity;

import lombok.Data;


/**
 * 表信息
 */
@Data
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

}
