package com.kuang.lesson01;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. 加载驱动，获得Drivermanager
        Class.forName("com.mysql.cj.jdbc.Driver"); //固定写法
        //2. 用户信息和url
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEnconding=utf8&useSSL=true&serverTimezone=UTC";
        String username = "root";
        String password = "diandian2710";
        //3. 连接成功, 数据库对象
            //connection 代表数据库
            //数据库设置自动提交
            //事物提交
            //事物回滚
        Connection connection = DriverManager.getConnection(url, username, password);
        //4. 执行SQL的对象
        Statement statement = connection.createStatement();
        //5. 执行SQL的对象 去 执行SQL, 可能存在结果， 查看返回的结果
        String sql = "select * from users";
        ResultSet resultSet = statement.executeQuery(sql);//返回结果集, 结果集中封装了我们全部的查询出来的结果
//        statement.execute(); execute any sql
//        statement.executeUpdate(); update, insert, delete -> return update rows
        while (resultSet.next()){
            System.out.println("id = " + resultSet.getObject("id"));
            System.out.println("name = " + resultSet.getObject("NAME"));
            System.out.println("password = " + resultSet.getObject("PASSWORD"));
            System.out.println("email = " + resultSet.getObject("EMAIL"));
            System.out.println("birthday = " + resultSet.getObject("birthday"));
        }
        //6. 释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
