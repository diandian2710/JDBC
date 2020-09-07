package com.kuang.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjection {
    public static void main(String[] args) throws SQLException {
        login("xiheng", "123456");
//        login("' or '1 = 1", "123456");
        //select * from users where `NAME` = '' or '1 = 1' and `PASSWORD` = '123456';
//        login("xiheng", "' or '1 = 1");
        //select * from users where `NAME` = 'xiheng' and `PASSWORD` = '' or '1 = 1';
        //sql存在漏洞， 会被攻击导致数据泄漏， ==SQL会被拼接or==


    }

    public static void login(String username, String password) throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            // select * from users where `NAME` = 'xiheng' and `PASSWORD` = '123456';
            String sql = "select * from users where `NAME` = '"+username+"' and `PASSWORD` = '" + password + "'";
            rs = st.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("PASSWORD"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
