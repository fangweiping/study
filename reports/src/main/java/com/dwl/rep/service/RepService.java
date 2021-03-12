package com.dwl.rep.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dwl.rep.common.Constants;
import com.dwl.rep.common.FreeMarker;
import com.dwl.rep.common.SqlEexecuter;
import com.dwl.rep.common.Strings;
import com.dwl.rep.dao.DataInfoMapper;
import com.dwl.rep.dao.HeaderInfoMapper;
import com.dwl.rep.dao.ReportDetailMapper;
import com.dwl.rep.dao.ReportInfoMapper;
import com.dwl.rep.pojo.DataInfo;
import com.dwl.rep.pojo.ReportDetail;
import com.dwl.rep.pojo.ReportInfo;

@Service("repService")
public class RepService {
	
	@Resource
	private ReportInfoMapper repMapper;
	
	@Resource
	private ReportDetailMapper reportDetailMapper;
	
	@Resource
	private HeaderInfoMapper headerInfoMapper;
	
	@Resource
	private DataInfoMapper dataInfoMapper;
	
	public List<ReportInfo> getRepList(){
		return repMapper.getInfoList();
	}
	
	public ReportInfo getInfoById(String repId){
		return repMapper.selectByPrimaryKey(repId);
	}
	
	@Cacheable(value="myCache",key="#repId",unless="#result.isCache == '0'")
	public ReportInfo getInfoWithDataById(String repId){
		ReportInfo reportInfo = repMapper.selectByPrimaryKey(repId);
		String[] dataId = Strings.splitIgnoreBlank(reportInfo.getDataId());
		List<Object> list = new ArrayList<>();
		//是否缓存
		if(Constants.CACHE.equals(reportInfo.getIsCache())){
			if(!Strings.isEmpty(reportInfo.getResult()))
				return reportInfo;
			for(String id :dataId){
				DataInfo dataInfo = dataInfoMapper.selectWithDbByPrimaryKey(id);
				if(Strings.isEmpty(dataInfo.getResult()))
					dataInfo.setResult(JSON.toJSONString(SqlEexecuter.getInstance().getResult(dataInfo)));
				List<Object> map = JSONArray.parseArray(dataInfo.getResult());
				list.addAll(map);
			}
			reportInfo.setData(list);
			if(Strings.isEmpty(reportInfo.getTemplet())){
				ReportInfo report = this.getInfoWithDeal(repId);
				reportInfo.setTemplet(FreeMarker.MakeHtml(report));
			}
			reportInfo.setResult(FreeMarker.setData(reportInfo.getTemplet(), reportInfo.getData()));
			repMapper.updateByPrimaryKeySelective(reportInfo);
		}else{
			for(String id :dataId){
				DataInfo dataInfo = dataInfoMapper.selectWithDbByPrimaryKey(id);
				dataInfo.setResult(JSON.toJSONString(SqlEexecuter.getInstance().getResult(dataInfo)));
				List<Object> map = JSONArray.parseArray(dataInfo.getResult());
				list.addAll(map);
			}
			reportInfo.setData(list);
			if(Strings.isEmpty(reportInfo.getTemplet())){
				ReportInfo report = this.getInfoWithDeal(repId);
				reportInfo.setTemplet(FreeMarker.MakeHtml(report));
			}
			reportInfo.setResult(FreeMarker.setData(reportInfo.getTemplet(), reportInfo.getData()));
		}
		return reportInfo;
	}
	
	public ReportInfo getInfoWithDeal(String repId){
		ReportInfo reportInfo = repMapper.selectWithDetailById(repId);
		reportInfo.setHasSecHead1("0");
		reportInfo.setHasSecHead2("0");
		reportInfo.getDetails().forEach(info->{
			info.setHeaderInfo(headerInfoMapper.selectWithDetailByPrimaryKey(info.getHeaderId()));
			if(!Strings.isEmpty(info.getSecHeaderId())){
				info.setSecHeaderInfo(headerInfoMapper.selectWithDetailByPrimaryKey(info.getSecHeaderId()));
				if("0".equals(info.getType()))
					reportInfo.setHasSecHead1("1");//拥有2级横表头
				else
					reportInfo.setHasSecHead2("1");//拥有2级竖表头		
			}
		});
		return reportInfo;
	}
	
	public ReportInfo getInfoWithDetail(String repId){
		ReportInfo reportInfo = repMapper.selectWithDetailById(repId);
		reportInfo.getDetails().forEach(info->{
			info.setHeaderInfo(headerInfoMapper.selectWithDetailByPrimaryKey(info.getHeaderId()));
			if(!Strings.isEmpty(info.getSecHeaderId())){
				info.setSecHeaderInfo(headerInfoMapper.selectWithDetailByPrimaryKey(info.getSecHeaderId()));		
			}
		});
		reportInfo.init();
		return reportInfo;
	}
	
	@Transactional
	@CacheEvict(value="myCache",key="#reportInfo.repId")
	public int updateRepInfo(ReportInfo reportInfo){
		reportDetailMapper.deleteByRepId(reportInfo.getRepId());
		List<ReportDetail> list = reportInfo.getDetails();
		for(int i=0;i<list.size();i++){
			if(!Strings.isEmpty(list.get(i).getHeaderId())){
				list.get(i).setId(UUID.randomUUID().toString());
				list.get(i).setRepId(reportInfo.getRepId());
				list.get(i).setOrders(i);
				reportDetailMapper.insert(list.get(i));
			}
		}
		reportInfo.setUpdateTime(new Date());
		return repMapper.updateByPrimaryKey(reportInfo);
	}
	
	
	public int updateRepInfoOnly(ReportInfo reportInfo){
		reportInfo.setUpdateTime(new Date());
		return repMapper.updateByPrimaryKeySelective(reportInfo);
	}
	
	@Transactional
	public int insertRepInfo(ReportInfo reportInfo){
		List<ReportDetail> list = reportInfo.getDetails();
		for(int i=0;i<list.size();i++){
			if(!Strings.isEmpty(list.get(i).getHeaderId())){
				list.get(i).setId(UUID.randomUUID().toString());
				list.get(i).setRepId(reportInfo.getRepId());
				list.get(i).setOrders(i);
				reportDetailMapper.insert(list.get(i));
			}
		}
		return repMapper.insert(reportInfo);
	}
	
	@Transactional
	@CacheEvict(value="myCache",key="#repId")
	public int deleteRepInfo(String repId){
		reportDetailMapper.deleteByRepId(repId);
		return repMapper.deleteByPrimaryKey(repId);
	}
	
	
	public List<ReportInfo> getCacheRepInfo(){
		return repMapper.selectCacheRepInfo();
	}
	
	/**
	 * 用于定时任务缓存
	 * @param repId
	 * @return
	 */
	@CachePut(value="myCache",key="#repId")
	public ReportInfo updateCache(String repId){
		ReportInfo reportInfo = repMapper.selectByPrimaryKey(repId);
		String[] dataId = Strings.splitIgnoreBlank(reportInfo.getDataId());
		List<Object> list = new ArrayList<>();
		for(String id :dataId){
			DataInfo dataInfo = dataInfoMapper.selectWithDbByPrimaryKey(id);
			dataInfo.setResult(JSON.toJSONString(SqlEexecuter.getInstance().getResult(dataInfo)));
			List<Object> map = JSONArray.parseArray(dataInfo.getResult());
			list.addAll(map);
		}
		reportInfo.setData(list);
		if(Strings.isEmpty(reportInfo.getTemplet())){
			ReportInfo report = this.getInfoWithDeal(repId);
			reportInfo.setTemplet(FreeMarker.MakeHtml(report));
		}
		reportInfo.setResult(FreeMarker.setData(reportInfo.getTemplet(), reportInfo.getData()));
		reportInfo.setUpdateTime(new Date());
		repMapper.updateByPrimaryKeySelective(reportInfo);
		return reportInfo;
	}
}
