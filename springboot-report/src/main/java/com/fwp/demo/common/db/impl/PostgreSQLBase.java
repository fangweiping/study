package com.fwp.demo.common.db.impl;


import com.fwp.demo.entity.DbInfo;
import com.fwp.demo.common.db.DataBase;
import com.fwp.demo.entity.TableInfo;

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
