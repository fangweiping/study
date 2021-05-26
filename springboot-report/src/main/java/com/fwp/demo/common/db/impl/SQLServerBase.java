package com.fwp.demo.common.db.impl;


import com.fwp.demo.entity.DbInfo;
import com.fwp.demo.common.db.DataBase;
import com.fwp.demo.entity.TableInfo;

import java.util.List;

public class SQLServerBase implements DataBase {

	@Override
	public String getDriver() {
		return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}

	@Override
	public String getLinkSql() {
		return "select 1";
	}

	@Override
	public String getDbType() {
		return "SQLServer";
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
