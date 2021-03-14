package com.hand.report.common.db.impl;


import com.hand.report.common.DataBase;

public class OracleBase implements DataBase {

	@Override
	public String getDriver() {
		return "oracle.jdbc.driver.OracleDriver";
	}

	@Override
	public String getLinkSql() {
		return "select 1 from dual";
	}

	@Override
	public String getDbType() {
		return "Oracle";
	}

}
