package com.fwp.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbInfo {
    /**
     * 主键
     */
    private String id;

    /**
     * 数据源名称
     */
    private String dbName;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 数据库地址
     */
    private String dbAddress;

    /**
     * 用户名
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     *
     */
    private String level;

    /**
     * 状态
     */
    private String status;
}
