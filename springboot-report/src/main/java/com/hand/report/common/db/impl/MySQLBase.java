package com.hand.report.common.db.impl;

import com.hand.report.common.db.DataBase;
import com.hand.report.common.db.DataBaseFactory;
import com.hand.report.entity.DbInfo;
import com.hand.report.entity.TableInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class MySQLBase implements DataBase {

    @Override
    public String getDriver() { return "com.mysql.jdbc.Driver"; }

    @Override
    public String getLinkSql() {
        return "select 1";
    }

    @Override
    public String getDbType() {
        return "MySQL";
    }

    @Override
    public List<TableInfo> getTableInfo(DbInfo dbInfo) {
        try (Connection connection =  DataBaseFactory.getInstance().getConnection(dbInfo);
             Statement statement = connection.createStatement()
        ) {
            String sql = "select table_name tableName,table_comment tableComment from information_schema.tables where table_schema = (select database())";
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            List<TableInfo> list = handResult(resultSet, new TableInfo());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TableInfo> getTableInfo(TableInfo tableInfo) {
        try (Connection connection = DataBaseFactory.getInstance().getConnection(DbInfo.builder().id(tableInfo.getDbId()).build());
             PreparedStatement statement = connection.prepareStatement("select table_name tableName,column_name columnName,column_comment columnComment from information_schema.columns where table_name = ?")
        ) {
            statement.setString(1, tableInfo.getTableName());
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            List<TableInfo> list = handResult(resultSet, new TableInfo());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
