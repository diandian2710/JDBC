package com.kuang.lesson05.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestC3P0 {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        PreparedStatement st = null;
        try {
             conn = JdbcUtils_C3P0.getConnection();
             String sql = "insert into users(`id`, `NAME`, `PASSWORD`, `email`, `birthday`) values (?,?,?,?,?)";
             st = conn.prepareStatement(sql);

             st.setInt(1,8);
             st.setString(2,"shdasda");
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
            JdbcUtils_C3P0.release(conn,st,null);
        }
    }
}
