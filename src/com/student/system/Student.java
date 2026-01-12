package com.student.system;

public class Student {
    private int id;
    private String name;
    private String gender;
    private String className;
    private double mathScore;
    private double javaScore;

    public Student() {}

    public Student(String name, String gender, String className, double mathScore, double javaScore) {
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.mathScore = mathScore;
        this.javaScore = javaScore;
    }

    // Getter 和 Setter（IDEA 可以自动生成）
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public double getMathScore() { return mathScore; }
    public void setMathScore(double mathScore) { this.mathScore = mathScore; }

    public double getJavaScore() { return javaScore; }
    public void setJavaScore(double javaScore) { this.javaScore = javaScore; }

    public double getAverageScore() {
        return (mathScore + javaScore) / 2.0;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, 姓名: %s, 性别: %s, 班级: %s, 数学: %.2f, Java: %.2f, 平均分: %.2f",
                id, name, gender, className, mathScore, javaScore, getAverageScore());
    }
}
