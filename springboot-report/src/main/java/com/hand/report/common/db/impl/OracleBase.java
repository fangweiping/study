package com.hand.report.common.db.impl;


import com.hand.report.common.db.DataBase;
import com.hand.report.entity.DbInfo;
import com.hand.report.entity.TableInfo;

import java.util.List;

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

    @Override
    public List<TableInfo> getTableInfo(DbInfo dbInfo) {
        return null;
    }

    @Override
    public List<TableInfo> getTableInfo(TableInfo tableInfo) {
        return null;
    }

}
