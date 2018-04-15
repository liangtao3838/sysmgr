package com.sys.mgr.utils;

import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.Statement;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 17:55 2018/4/15
 */
public class JdbcUtils {

    /*使用静态代码块完成驱动的加载*/
    static {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*提供连接的方法*/
    public static Connection getConnection() {
        Connection con = null;
        try {
            //连接指定的MMySQL数据库，三个参数分别是：数据库地址、账号、密码
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.103:3306/bank?useUnicode=true&characterEncoding=utf8", "root", "liangtao");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    /*关闭连接的方法*/
    public static void close(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (stmt != null)
                stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (con != null)
                con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}