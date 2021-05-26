package com.fwp.demo.common.db.impl;


import com.fwp.demo.entity.DbInfo;
import com.fwp.demo.common.db.DataBase;
import com.fwp.demo.entity.TableInfo;

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
