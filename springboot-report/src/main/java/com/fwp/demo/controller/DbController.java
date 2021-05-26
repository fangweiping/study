package com.fwp.demo.controller;


import com.fwp.demo.common.db.DataBaseFactory;
import com.fwp.demo.common.util.Const;
import com.fwp.demo.common.util.ResultFactory;
import com.fwp.demo.common.util.ResultObject;
import com.fwp.demo.entity.DbInfo;
import com.fwp.demo.service.DbService;
import com.fwp.demo.service.NumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fwp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/3/16 1:18 :00
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
     *
     * @return
     */
    @GetMapping("/getInfoList")
    public ResultObject<List<DbInfo>> getInfoList() {
        return ResultFactory.success(dbService.getInfoList());
    }

    /**
     * 编辑数据源
     *
     * @param dbId
     * @return
     */
    @GetMapping("/toDbDetail")
    public ResultObject<DbInfo> toDbDetail(@RequestParam("dbId") String dbId) {
        return ResultFactory.success(dbService.getInfoById(dbId));
    }

    /**
     * 保存数据源
     *
     * @param dbInfo
     * @return
     */
    @PostMapping("/toDbSave")
    public ResultObject addOrEdit(@RequestBody DbInfo dbInfo) {
        if (StringUtils.isEmpty(dbInfo.getId())) {
            dbInfo.setId(numService.getNum("DB"));
            dbService.saveDbInfo(dbInfo);
        } else {
            dbService.updateDbInfo(dbInfo);
            DataBaseFactory.getInstance().removeDataSource(dbInfo);
        }
        return ResultFactory.success();
    }

    /**
     * 删除数据源
     *
     * @param dbId
     * @return
     */
    @RequestMapping("/delDbInfo")
    public ResultObject deleteDbInfo(@RequestParam("dbId") String dbId) {
        if (dbService.isDbUsed(dbId)) {
            return ResultFactory.error(Const.DELETE_FAILED);
        }
        DataBaseFactory.getInstance().removeDataSource(dbService.getInfoById(dbId));
        if (dbService.delectInfoById(dbId) > 0)
            return ResultFactory.success(Const.DELETE_SUCESS);
        return ResultFactory.error(Const.DELETE_FAILED);
    }

    /**
     * 测试数据库连接
     *
     * @param dbId
     * @return
     */
    @RequestMapping("/connDataBase")
    public ResultObject connDataBase(@RequestParam("dbId") String dbId) {
        DbInfo dbInfo = dbService.getInfoById(dbId);
        if (DataBaseFactory.getInstance().testConnection(dbInfo))
            return ResultFactory.success(Const.CONNECTION_SUCESS);
        else
            return ResultFactory.success(Const.CONNECTION_FAILED);
    }
}
