package com.hand.report.controller;


import com.hand.report.common.DataBaseFactory;
import com.hand.report.entity.DbInfo;
import com.hand.report.service.DbService;
import com.hand.report.service.NumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据库控制器
 * @author dwl
 *
 */
@RestController
@RequestMapping("/db")
public class DbController {

	@Autowired
	private DbService dbService;

	@Autowired
	private NumService numService;

    /**
     * 获取数据源列表
     * @return
     */
	@RequestMapping("/getInfoList")
	public List<DbInfo> getInfoList(){
	    return 	dbService.getInfoList();
	}

    /**
     * 编辑数据源
     * @param dbInfo
     * @return
     */
	@RequestMapping("/toDbDetail")
	public DbInfo toDbDetail(@RequestBody DbInfo dbInfo){
      return dbService.getInfoById(dbInfo.getId());
	}

	/**
	 * 保存数据源
	 * @param dbInfo
	 * @return
	 */
	@RequestMapping("/toDbSave")
	public String addOrEdit(DbInfo dbInfo){
		if(StringUtils.isEmpty(dbInfo.getId())){
			dbInfo.setId(numService.getNum("DB"));
			dbService.saveDbInfo(dbInfo);
		}else{
			dbService.updateDbInfo(dbInfo);
			DataBaseFactory.getInstance().removeDataSource(dbInfo);
		}
		return null;
	}

	/**
	 * 删除数据源
	 * @param dbId
	 * @return
	 */
	@RequestMapping("/delDbInfo")
	public String delectDbInfo(String dbId){
		if(dbService.isDbUsed(dbId))
			return "dataBase is used!";
		DataBaseFactory.getInstance().removeDataSource(dbService.getInfoById(dbId));
		if(dbService.delectInfoById(dbId)>0)
			return "delete success!";
		return "delete failed!";
	}


	/**
	 * 测试数据库连接
	 * @param dbId
	 * @return
	 */
	@RequestMapping("/connDataBase")
	public String connDataBase(String dbId){
		DbInfo dbInfo = dbService.getInfoById(dbId);
		if(DataBaseFactory.getInstance().testConnection(dbInfo))
			return "connection success!";
		else
			return "connection failed";
	}

}
