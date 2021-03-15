package com.hand.report.common.db.impl;


import com.hand.report.common.db.DataBase;
import com.hand.report.entity.DbInfo;
import com.hand.report.entity.TableInfo;

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
