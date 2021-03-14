package com.hand.report.service.impl;

import com.hand.report.dao.NumInfoMapper;
import com.hand.report.entity.NumInfo;
import com.hand.report.service.NumService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service("numService")
public class NumServiceImpl implements NumService {

	@Resource
	private NumInfoMapper numInfoMapper;

	/**
	 * 取号
	 * 假装没并发:）
	 * @param id
	 * @return
	 */
	@Override
	public String getNum(String id){
		NumInfo numInfo = null;
		if(!StringUtils.isEmpty(id)){
			numInfo = numInfoMapper.selectByPrimaryKey(id);
			if (null == numInfo) {
				numInfo = new NumInfo();
				numInfo.setNumId(id);
				numInfo.setNumNow(0);
				numInfo.setNumLen(4);
				numInfo.setNumPre(id);
				numInfoMapper.insert(numInfo);
			}
			numInfo.setNumNow(numInfo.getNumNow()+1);
			numInfoMapper.updateByPrimaryKey(numInfo);
			String suffix = "000"+numInfo.getNumNow();
			StringBuilder builder = new StringBuilder(id);
			builder.append(suffix.substring(suffix.length()-4));
			return builder.toString();
		}
		return null;
	}
}
