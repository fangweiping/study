package com.hand.report.common.db.impl;


import com.hand.report.common.DataBase;

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

}
