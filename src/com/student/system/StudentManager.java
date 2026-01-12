package com.student.system;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    public static boolean addStudent(Student student) {
        String sql = "INSERT INTO student (name, gender, class_name, math_score, java_score) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("添加学生失败: " + e.getMessage());
            return false;
        }
    }

    public static Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        Student student = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class_name"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
            }

        } catch (SQLException e) {
            System.out.println("查询学生失败: " + e.getMessage());
        }
        return student;
    }

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class_name"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("获取学生列表失败: " + e.getMessage());
        }
        return students;
    }

    public static void calculateAverageScores() {
        String sql = "SELECT AVG(math_score) as avg_math, AVG(java_score) as avg_java FROM student";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                double avgMath = rs.getDouble("avg_math");
                double avgJava = rs.getDouble("avg_java");
                System.out.printf("数学平均分: %.2f\n", avgMath);
                System.out.printf("Java平均分: %.2f\n", avgJava);
                System.out.printf("总平均分: %.2f\n", (avgMath + avgJava) / 2);
            }

        } catch (SQLException e) {
            System.out.println("计算平均分失败: " + e.getMessage());
        }
    }
}