package com.hand.report.common.db.impl;


import com.hand.report.common.db.DataBase;
import com.hand.report.entity.DbInfo;
import com.hand.report.entity.TableInfo;

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
