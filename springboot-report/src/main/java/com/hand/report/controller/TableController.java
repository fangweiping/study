package com.hand.report.controller;

import com.hand.report.common.db.DataBase;
import com.hand.report.common.db.DataBaseFactory;
import com.hand.report.common.util.ResultFactory;
import com.hand.report.common.util.ResultObject;
import com.hand.report.dao.TableMapper;
import com.hand.report.entity.DbInfo;
import com.hand.report.entity.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private TableMapper tableMapper;

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

}
