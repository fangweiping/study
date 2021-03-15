package com.hand.report.common.db;

import com.hand.report.entity.DbInfo;
import com.hand.report.entity.TableInfo;
import org.apache.commons.dbcp.BasicDataSource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 数据源
 * @author dwl
 *
 */
public interface DataBase {

	/**
	 * 返回驱动
	 * @return
	 */
	String getDriver();

	/**
	 * 返回测试连接sql
	 * @return
	 */
	String getLinkSql();

	/**
	 * 返回数据库类型
	 * @return
	 */
	String getDbType();

    /**
     * 返回表名
     * @return
     */
     List<TableInfo> getTableInfo(DbInfo dbInfo);

    /**
     * 返回字段
     */
    List<TableInfo> getTableInfo(TableInfo tableInfo);

    /**
	 * 创建数据源
	 * @return
	 */
	default BasicDataSource createDataSource(DbInfo dbInfo){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.getDriver());
		dataSource.setUrl(dbInfo.getDbAddress());
		dataSource.setUsername(dbInfo.getUserId());
		dataSource.setPassword(dbInfo.getPassword());
		if("1".equals(dbInfo.getLevel())){
			dataSource.setInitialSize(5);
			dataSource.setMinIdle(5);
	        dataSource.setMaxActive(10);
	        dataSource.setMaxIdle(20);
	        dataSource.setMaxWait(1000);
		}else if("2".equals(dbInfo.getLevel())){
			dataSource.setInitialSize(10);
			dataSource.setMinIdle(10);
	        dataSource.setMaxActive(20);
	        dataSource.setMaxIdle(40);
	        dataSource.setMaxWait(1000);
		}else{
			dataSource.setInitialSize(20);
			dataSource.setMinIdle(20);
	        dataSource.setMaxActive(50);
	        dataSource.setMaxIdle(100);
	        dataSource.setMaxWait(1000);
		}
		return dataSource;

	}

    /**
     * 处理结果集
     */
    default <T> List<T> handResult(ResultSet resultSet,T resultType) throws Exception {
        if(resultSet==null)
            return null;
        Class<?> c = resultType.getClass();
        /* Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            c = (Class<T>) p[0];
        }*/
        List<T> list = new ArrayList<>();
        Map<String, Field> fieldMap = Arrays.stream(c.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity()));
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            columnNames.add(metaData.getColumnName(i));
        }
        while (resultSet.next()) {
            columnNames.forEach(columnName->{
                try {
                    fieldMap.get(columnName).set(resultType, resultSet.getString(columnName));
                    list.add(resultType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }

    /**
     * 执行sql
     */
    default  <T> List<T> executeSql(String dbId, String sql,T resultType) {
        try (Connection connection =  DataBaseFactory.getInstance().getConnection(DbInfo.builder().id(dbId).build());
             Statement statement = connection.createStatement()
        ) {
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            return  handResult(resultSet, resultType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
