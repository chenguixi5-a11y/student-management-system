package com.student.system;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== 学生成绩管理系统 ======");
            System.out.println("1. 添加学生");
            System.out.println("2. 按ID查询学生");
            System.out.println("3. 显示所有学生");
            System.out.println("4. 计算各科平均分");
            System.out.println("5. 退出");
            System.out.print("请选择操作: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> searchStudent(scanner);
                case 3 -> showAllStudents();
                case 4 -> calculateAverages();
                case 5 -> {
                    System.out.println("感谢使用！");
                    scanner.close();
                    return;
                }
                default -> System.out.println("无效选择！");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("姓名: ");
        String name = scanner.nextLine();

        System.out.print("性别: ");
        String gender = scanner.nextLine();

        System.out.print("班级: ");
        String className = scanner.nextLine();

        System.out.print("数学成绩: ");
        double mathScore = scanner.nextDouble();

        System.out.print("Java成绩: ");
        double javaScore = scanner.nextDouble();
        scanner.nextLine();

        Student student = new Student(name, gender, className, mathScore, javaScore);
        System.out.println(StudentManager.addStudent(student) ? "添加成功！" : "添加失败！");
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("输入学生ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = StudentManager.getStudentById(id);
        System.out.println(student != null ? student : "未找到！");
    }

    private static void showAllStudents() {
        List<Student> students = StudentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("暂无学生信息！");
        } else {
            System.out.println("====== 学生列表 ======");
            students.forEach(System.out::println);
            System.out.println("共 " + students.size() + " 名学生");
        }
    }

    private static void calculateAverages() {
        System.out.println("====== 成绩统计 ======");
        StudentManager.calculateAverageScores();
    }
}