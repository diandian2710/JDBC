package com.kuang.lesson03;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sql注入 {
    public static void main(String[] args) throws SQLException {

        login("xiheng", "123456");
//        login("' or '1 = 1", "123456");
        //select * from users where `NAME` = '' or '1 = 1' and `PASSWORD` = '123456';
//        login("xiheng", "' or '1 = 1");
        //select * from users where `NAME` = 'xiheng' and `PASSWORD` = '' or '1 = 1';
        //sql存在漏洞， 会被攻击导致数据泄漏， ==SQL会被拼接or==


    }

    //登陆业务
    public static void login(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            //PreparedStatement 防止SQL注入的本质，把传递进来的参数当作字符
            //假设其中存在转义字符，就直接忽略， 引号会被直接转义
            String sql = "select * from users where `NAME` = ?  and `PASSWORD` = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2,password);
            rs = st.executeQuery();
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
