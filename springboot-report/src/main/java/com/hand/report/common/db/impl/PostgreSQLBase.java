package com.hand.report.common.db.impl;


import com.hand.report.common.db.DataBase;
import com.hand.report.entity.DbInfo;
import com.hand.report.entity.TableInfo;

import java.util.List;

public class PostgreSQLBase implements DataBase {

	@Override
	public String getDriver() {
		return "org.postgresql.Driver";
	}

	@Override
	public String getLinkSql() {
		return "select version()";
	}

	@Override
	public String getDbType() {
		return "PostgreSQL";
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
