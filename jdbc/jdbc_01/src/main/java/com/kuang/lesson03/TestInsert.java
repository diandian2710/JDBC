package com.kuang.lesson03;

import com.kuang.lesson02.utils.JdbcUtils;
import java.util.Date;
import java.sql.*;

public class TestInsert {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = JdbcUtils.getConnection();
            //区别
            //使用？占位符替代参数
            String sql = "insert into users(id, `NAME`, `PASSWORD`, `email`, `birthday`) values(?,?,?,?,?)";
            st = conn.prepareStatement(sql); //预编译SQL， 先写SQL，然后不执行
            //手动给参数赋值
            st.setInt(1, 5);
            st.setString(2, "weishao");
            st.setString(3, "123456");
            st.setString(4, "haha@gamil.com");
            //注意点 sql.Date 数据库
            // util.Date java new Date().getTime()获得时间戳
            st.setDate(5,new java.sql.Date(new Date().getTime()));
            int i = st.executeUpdate();
            if (i > 0){
                System.out.println("更新成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st, null);

        }
    }
}
