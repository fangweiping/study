package com.hand.report.common.db.impl;


import com.hand.report.common.DataBase;

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

}
