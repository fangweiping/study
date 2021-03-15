package com.hand.report.common;

import com.hand.report.common.db.impl.*;
import com.hand.report.entity.DbInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DataBaseFactory implements Serializable {

    private DataBaseFactory() {

    }

    private static DataBaseFactory dataBaseFactory = null;

    private static Map<String, DbInfo> dbInfoMap = new HashMap<>();

    public enum BaseType {

        MySQL, Oracle, PostgreSQL, SQLite, SQLServer

    }

    /**
     * 加载数据源信息
     */
    static {
        readObject();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static DataBaseFactory getInstance() {
        if (dataBaseFactory == null) {
            synchronized (DataBaseFactory.class) {
                if (dataBaseFactory == null)
                    dataBaseFactory = new DataBaseFactory();
            }
        }
        return dataBaseFactory;
    }


    /**
     * 获取数据库信息
     *
     * @param dbInfo
     * @return
     */
    public DataBase getDataBase(DbInfo dbInfo) {
        DataBase dataBase = null;
        BaseType type = BaseType.valueOf(dbInfo.getDbType());
        switch (type) {
            case MySQL:
                dataBase = new MySQLBase();
                break;
            case Oracle:
                dataBase = new OracleBase();
                break;
            case PostgreSQL:
                dataBase = new PostgreSQLBase();
                break;
            case SQLite:
                dataBase = new SQLiteBase();
                break;
            case SQLServer:
                dataBase = new SQLServerBase();
                break;
            default:
                break;
        }
        return dataBase;
    }

    /**
     * 获取数据库连接
     *
     * @param dbInfo
     * @return
     * @throws SQLException
     */
    public Connection getConnection(DbInfo dbInfo) {
        Connection connection = null;
        if (!dbInfoMap.containsKey(dbInfo.getId()))
            addDataSource(dbInfo);
        try {
            connection = dbInfoMap.get(dbInfo.getId()).getDataSource().getConnection();
        } catch (SQLException e) {
            removeDataSource(dbInfo);
            log.info(dbInfo.getDbName() + " -无法获取连接！");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 添加数据源
     *
     * @param dbInfo
     */
    public void addDataSource(DbInfo dbInfo) {
        synchronized (DataBaseFactory.class) {
            if (!dbInfoMap.containsKey(dbInfo.getId())) {
                dbInfo.setDataSource(createDataSource(dbInfo));
                dbInfoMap.put(dbInfo.getId(), dbInfo);
                writeObjet();
            }
        }
    }

    /**
     * 创建数据源
     *
     * @param dbInfo
     * @return
     */
    public BasicDataSource createDataSource(DbInfo dbInfo) {
        log.info("创建数据源：" + dbInfo.getDbName());
        BasicDataSource dataSource = getDataBase(dbInfo).createDataSource(dbInfo);
        log.info(dbInfo.getDbName() + " - 创建成功");
        return dataSource;

    }


    /**
     * 移除数据源
     *
     * @param dbInfo
     */
    public void removeDataSource(DbInfo dbInfo) {
        BasicDataSource dataSource = (BasicDataSource) dbInfoMap.get(dbInfo.getId()).getDataSource();
        if (dataSource != null) {
            try {
                dataSource.close();
                dbInfoMap.remove(dbInfo.getId());
                writeObjet();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.info(dbInfo.getDbName() + " - 移除成功");
    }


    /**
     * 测试连接
     *
     * @param dbInfo
     * @throws SQLException
     */
    public boolean testConnection(DbInfo dbInfo) {
        Connection conn = getConnection(dbInfo);
        if (conn == null)
            return false;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(getDataBase(dbInfo).getLinkSql());
            if (rs.next())
                log.info(getDataBase(dbInfo).getLinkSql() + " ----> " + rs.getString(1));
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 序列化
     *
     * @return
     */
    void writeObjet() {
        try (FileOutputStream fos = new FileOutputStream("dataSource.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(dbInfoMap);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     *
     * @return
     */
    public static void readObject() {
        try (FileInputStream fis = new FileInputStream("dataSource.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            dbInfoMap = (Map<String, DbInfo>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回数据源信息
     * @return
     */
    public static List<DbInfo> getDataSource() {
        return (List<DbInfo>) dbInfoMap.values();
    }

    /**
     * 返回数据源信息
     * @return
     */
    public static DataSource getDataSource(String dbId) {
        return dbInfoMap.get(dbId).getDataSource();
    }
}
