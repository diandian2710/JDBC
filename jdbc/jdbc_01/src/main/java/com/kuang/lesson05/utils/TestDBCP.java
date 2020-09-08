package com.kuang.lesson05.utils;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDBCP {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        PreparedStatement st = null;
        try {
             conn = JdbcUtils_DBCP.getConnection();
             String sql = "insert into users(`id`, `NAME`, `PASSWORD`, `email`, `birthday`) values (?,?,?,?,?)";
             st = conn.prepareStatement(sql);

             st.setInt(1,6);
             st.setString(2,"hahaha");
             st.setString(3,"123456");
             st.setString(4,"xiheng@qq.com");
             st.setDate(5, new Date(new java.util.Date().getTime()));
             int i = st.executeUpdate();
             if(i>0){
                 System.out.println("插入成功");
             }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils_DBCP.release(conn,st,null);
        }
    }
}
