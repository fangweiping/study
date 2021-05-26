package com.fwp.demo.common.db.impl;


import com.fwp.demo.entity.DbInfo;
import com.fwp.demo.common.db.DataBase;
import com.fwp.demo.entity.TableInfo;

import java.util.List;

public class SQLiteBase implements DataBase {

	@Override
	public String getDriver() {
		return "org.sqlite.JDBC";
	}

	@Override
	public String getLinkSql() {
		return "SELECT 'x'";
	}

	@Override
	public String getDbType() {
		return "SQLite";
	}

    @Override
    public List<TableInfo> getTableInfo(DbInfo dbInfo) {
        return null;
    }

    @Override
    public List<TableInfo> getTableInfo(TableInfo tableInfo) {
        return null;
    }

}
