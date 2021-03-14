package com.hand.report.common.db.impl;

import com.hand.report.common.DataBase;

public class MySQLBase implements DataBase {

	@Override
	public String getDriver() {
		return "com.mysql.jdbc.Driver";
	}

	@Override
	public String getLinkSql() {
		return "select 1";
	}

	@Override
	public String getDbType() {
		return "MySQL";
	}

}
