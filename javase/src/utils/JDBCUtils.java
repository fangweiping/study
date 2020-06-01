package utils;/**
 * @author Fang WeiPing
 * @date 2020/5/29 11:53
 */

import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/29 11:53 
 */
public class JDBCUtils {

    @Test
    public void JDBCTest() {
        try {
            // 1. 注册驱动
            // 使用java.sql.DriverManager类的静态方法registerDriver(Driver driver)
            // Driver是一个接口,参数传递:MySQL驱动程序的实现类
            // DriverManager.registerDriver(new Driver());
            // 查看驱动类源码,注册两次驱动,浪费资源
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获得连接
            // uri:数据库地址 jdbc:mysql://连接主机ip:端口号//数据库名字
            String url = "jdbc:mysql://172.19.25.196/sbc-goods";  //we7shop
            // static Connection getConnection(String url, String user, String password)
            // 返回值是java.sql.Connection接口的实现类,在MySQL驱动程序中
            //we7shop   账号
            //E6EZ7352Jo427uMt  密码
            Connection conn = DriverManager.getConnection(url, "root", "Wmi@2019");
            System.out.println(conn);// com.mysql.jdbc.JDBC4Connection@10d1f30
            // 3. 获得语句执行平台,通过数据库连接对象,获取到SQL语句的执行者对象
            //conn对象,调用方法 Statement createStatement() 获取Statement对象,将SQL语句发送到数据库
            //返回的是Statement接口的实现类对象,在MySQL驱动程序中
            Statement stat = conn.createStatement();
            System.out.println(stat);//com.mysql.jdbc.StatementImpl@137bc9
            // 4. 执行sql语句
            //通过执行者对象调用方法执行SQL语句,获取结果
            //int executeUpdate(String sql)  执行数据库中的SQL语句,仅限于insert,update,delete
            //返回值int,操作成功数据库的行数
            boolean execute = stat.execute("select id, goodssn, total from ims_ewei_shop_goods limit 0,1");
            ResultSet resultSet = stat.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String goodssn  = resultSet.getString("goodssn");//goods_info_no
                int total = resultSet.getInt("total");//stock
                System.out.println("id:"+id+",sku编号:"+goodssn+",库存数量:"+total);
            }

            // 5. 释放资源
            stat.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
