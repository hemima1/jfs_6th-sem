package com.example;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/skillnext_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";  // change if needed

    // Add student
    public void addStudent(Student s) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO student_jfs (name, sem, department) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getSem());
        stmt.setString(3, s.getDepartment());
        stmt.executeUpdate();
        conn.close();
    }

    // Fetch all students
    public List<Student> getAllStudents() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student_jfs");

        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSem(rs.getInt("sem"));
            s.setDepartment(rs.getString("department"));
            list.add(s);
        }

        conn.close();
        return list;
    }

    // Delete student
    public void deleteStudent(int id) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "DELETE FROM student_jfs WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        conn.close();
    }

    // Update student
    public void updateStudent(Student s) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "UPDATE student_jfs SET name=?, sem=?, department=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getSem());
        stmt.setString(3, s.getDepartment());
        stmt.setInt(4, s.getId());
        stmt.executeUpdate();
        conn.close();
    }
}