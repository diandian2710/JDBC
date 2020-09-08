package com.kuang.lesson04;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn =  JdbcUtils.getConnection();
            //关闭数据的自动提交功能, 自动开启事物
            conn.setAutoCommit(false);
            String sql = "update account set `money` = `money` - 100 where `NAME` = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, "A");
            st.executeUpdate();
            //业务完毕，提交事物
            conn.commit();
            System.out.println("成功");

        } catch (SQLException throwables) {
            //若失败，则默认回滚
            try {
                conn.rollback(); //如果失败则回滚事物
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            conn.setAutoCommit(true); //关闭事物
            JdbcUtils.release(conn,st,rs);
        }
    }
}
