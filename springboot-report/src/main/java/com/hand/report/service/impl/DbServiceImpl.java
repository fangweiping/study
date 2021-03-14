package com.hand.report.service.impl;

import com.hand.report.dao.DbInfoMapper;
import com.hand.report.entity.DbInfo;
import com.hand.report.service.DbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dbService")
public class DbServiceImpl implements DbService {

	@Resource
	private DbInfoMapper dbInfoMapper;

	@Override
	public List<DbInfo> getInfoList(){
		return dbInfoMapper.getInfoList();
	}

    @Override
	public DbInfo getInfoById(String Id){
		return dbInfoMapper.selectByPrimaryKey(Id);
	}

    @Override
	public int saveDbInfo(DbInfo dnInfo){
		return dbInfoMapper.insert(dnInfo);
	}

    @Override
	public int updateDbInfo(DbInfo dbInfo){
		return dbInfoMapper.updateByPrimaryKey(dbInfo);
	}

    @Override
	public int delectInfoById(String Id){
		return dbInfoMapper.deleteByPrimaryKey(Id);
	}

	/**
	 * 判断数据源是否被使用
	 * @param Id
	 * @return 使用返回true
	 */
	@Override
	public boolean isDbUsed(String Id){
		int count = dbInfoMapper.usedCount(Id);
		if(count>0)
			return true;
		return false;
	}


}
