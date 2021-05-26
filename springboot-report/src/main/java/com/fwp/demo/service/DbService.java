package com.fwp.demo.service;

import com.fwp.demo.entity.DbInfo;

import java.util.List;

public interface DbService {
    List<DbInfo> getInfoList();

    DbInfo getInfoById(String Id);

    int saveDbInfo(DbInfo dnInfo);

    int updateDbInfo(DbInfo dbInfo);

    int delectInfoById(String Id);

    boolean isDbUsed(String Id);
}
