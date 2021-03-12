package com.dwl.rep.dao;

import java.util.List;

import com.dwl.rep.pojo.ChartInfo;

public interface ChartInfoMapper {
    int deleteByPrimaryKey(String chartId);

    int insert(ChartInfo record);

    int insertSelective(ChartInfo record);

    ChartInfo selectByPrimaryKey(String chartId);

    int updateByPrimaryKeySelective(ChartInfo record);

    int updateByPrimaryKey(ChartInfo record);
    
    List<ChartInfo> selectList();
    
    List<ChartInfo> selectCacheList();
    
}