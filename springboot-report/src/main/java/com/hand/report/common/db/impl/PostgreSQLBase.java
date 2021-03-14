package com.hand.report.common.db.impl;


import com.hand.report.common.DataBase;

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

}
