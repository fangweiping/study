package com.dwl.rep.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwl.rep.dao.DataInfoMapper;
import com.dwl.rep.pojo.DataInfo;

@Service("dataService")
public class DataService {
	
	@Resource
	private DataInfoMapper dataInfoMapper;
	
	public DataInfo getInfoById(String id){
		return dataInfoMapper.selectByPrimaryKey(id);
	}
	
	public DataInfo getInfoWithDbById(String id){
		return dataInfoMapper.selectWithDbByPrimaryKey(id);
	}
	
	public List<DataInfo> getInfoList(){
		return dataInfoMapper.selectInfoList();
	}
	
	public List<DataInfo> getInfoListWithDb(){
		return dataInfoMapper.selectInfoListWithDb();
	}
	
	public int saveData(DataInfo dataInfo) {
		return dataInfoMapper.insert(dataInfo);
	}
	
	public int updateData(DataInfo dataInfo) {
		dataInfo.setUpdateTime(new Date());
		return dataInfoMapper.updateByPrimaryKey(dataInfo);
	}
	
	public int delectInfoById(String id){
		return dataInfoMapper.deleteByPrimaryKey(id);
	}

}
