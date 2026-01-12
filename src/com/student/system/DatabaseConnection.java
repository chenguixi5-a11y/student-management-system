package com.student.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 修改这里：去掉密码（如果你的MySQL没密码）
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // 如果没密码就留空

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("数据库连接成功！");
            return conn;
        } catch (SQLException e) {
            System.out.println("数据库连接失败，使用模拟数据模式");
            System.out.println("错误详情: " + e.getMessage());
            return null; // 返回null，让程序继续用模拟数据运行
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) {}
        }
    }
}