package com.fwp.demo.controller;

import com.fwp.demo.common.db.DataBase;
import com.fwp.demo.common.db.DataBaseFactory;
import com.fwp.demo.common.util.ResultFactory;
import com.fwp.demo.common.util.ResultObject;
import com.fwp.demo.entity.BaseCount;
import com.fwp.demo.entity.DbInfo;
import com.fwp.demo.common.db.DataBase;
import com.fwp.demo.common.db.DataBaseFactory;
import com.fwp.demo.common.util.ResultFactory;
import com.fwp.demo.common.util.ResultObject;
import com.fwp.demo.dao.TableMapper;
import com.fwp.demo.entity.BaseCount;
import com.fwp.demo.entity.DbInfo;
import com.fwp.demo.entity.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fwp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/3/16 1:18 :00
 */
@RestController
@RequestMapping("/table")
public class TableController {

    //@Autowired
    //private TableMapper tableMapper;

    /**
     * 获取数据库表名
     *
     * @return
     */
    @GetMapping("/getTableName")
    public ResultObject<List<TableInfo>> getTableName(@RequestBody DbInfo dbInfo) {
        DataBase dataBase = DataBaseFactory.getInstance().getDataBase(dbInfo);
        return ResultFactory.success( dataBase.getTableInfo(dbInfo));
    }

    /**
     * 获取数据库表字段名
     *
     * @return
     */
    @GetMapping("/getColumnName")
    public ResultObject<List<TableInfo>> getColumnName(@RequestBody TableInfo tableInfo) {
        DataBase dataBase = DataBaseFactory.getInstance().getDataBase(DbInfo.builder().id(tableInfo.getDbId()).build());
        return ResultFactory.success( dataBase.getTableInfo(tableInfo));
    }

    /**
     * 统计
     *
     * @return
     */
    @PostMapping("/count")
    public ResultObject<List<BaseCount>> executeSql(@RequestBody TableInfo tableInfo) {
        DataBase dataBase = DataBaseFactory.getInstance().getDataBase(DbInfo.builder().id(tableInfo.getDbId()).build());
        return ResultFactory.success(dataBase.executeSql(tableInfo.getDbId(), tableInfo.getExecuteSql(), new BaseCount()));
    }

}
