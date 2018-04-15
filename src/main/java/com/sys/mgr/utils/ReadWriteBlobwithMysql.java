package com.sys.mgr.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadWriteBlobwithMysql
{
    private Connection con;
    private Statement stmt;

    public Statement getStmt()
    {
        return stmt;
    }

    public void setStmt(Statement stmt)
    {
        this.stmt = stmt;
    }

    public ResultSet getRs()
    {
        return rs;
    }

    public void setRs(ResultSet rs)
    {
        this.rs = rs;
    }

    ResultSet rs;

    public Connection getCon()
    {
        return con;
    }

    public void setCon(Connection con)
    {
        this.con = con;
    }

    public void insert(Connection con) throws SQLException
    {
        String fileName = "/Users/liangtao/Documents/技术资料/java基础/java反射";
        File file = new File(fileName);

        try
        {
            FileInputStream fis = new FileInputStream(file);
            String sql = "update trade_detail set xyxml=? WHERE id=1";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setBlob(1, fis,file.length());
            prest.execute();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public ReadWriteBlobwithMysql(Connection con)
    {
        this.setCon(con);
        try
        {
            stmt = con.createStatement();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Connection con = JdbcUtils.getConnection();
        ReadWriteBlobwithMysql dao = new ReadWriteBlobwithMysql(con);
        try
        {
            dao.insert(con);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JdbcUtils.close(dao.getRs(), dao.getStmt(), dao.getCon());
            System.out.println("完事了");
        }
    }

}