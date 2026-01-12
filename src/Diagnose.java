import java.sql.*;

public class Diagnose {
    public static void main(String[] args) {
        System.out.println("=== MySQL 连接诊断 ===");

        // 1. 测试驱动
        System.out.println("\n1. 测试驱动加载...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ 驱动加载成功");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ 驱动加载失败: " + e.getMessage());
            System.out.println("请确认 mysql-connector-j-9.1.0.jar 已添加到项目");
            return;
        }

        // 2. 测试多个连接URL
        String[] urls = {
                "jdbc:mysql://localhost:3306/student_management",
                "jdbc:mysql://localhost:3306/student_management?useSSL=false",
                "jdbc:mysql://127.0.0.1:3306/student_management",
                "jdbc:mysql://localhost:3306/mysql"  // 测试默认数据库
        };

        String user = "root";
        String password = "";  // 你的密码

        System.out.println("\n2. 测试数据库连接...");
        for (String url : urls) {
            System.out.print("尝试: " + url + " ... ");
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                System.out.println("✅ 成功");

                // 测试查询
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT DATABASE() as db");
                if (rs.next()) {
                    System.out.println("   当前数据库: " + rs.getString("db"));
                }
                rs.close();
                stmt.close();

                return; // 成功就退出

            } catch (SQLException e) {
                System.out.println("❌ 失败: " + e.getMessage());
            }
        }

        System.out.println("\n⚠️ 所有连接尝试都失败了！");
        System.out.println("\n可能原因：");
        System.out.println("1. MySQL服务未启动（在服务中启动 MySQL）");
        System.out.println("2. 端口不是3306（检查 my.ini 文件）");
        System.out.println("3. 用户名/密码错误（你安装时的密码）");
        System.out.println("4. 数据库不存在（先运行之前的SQL创建）");
    }
}
