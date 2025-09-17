package org.example.dao;

import org.example.dao.exceptions.NoValidName;
import org.example.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select {
    private final String URL = "jdbc:mariadb://localhost:3306/studentDAM";
    private final String USER = "root";
    private final String PASSWORD = "";

    public List<Student> allSaveStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, expediente FROM student";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String expediente = rs.getString("expediente");

                Student newStudent = new Student(id, name, expediente);

                students.add(newStudent);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return students;
    }

    public Student searchForName(String name) throws NoValidName {
        String sql = "SELECT id, name, expediente FROM student WHERE name LIKE ?";

        if (name == null ) {
            throw new NoValidName("Invalid name: cannot be empty");
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("name");
                String expediente = rs.getString("expediente");

                return new Student(id, nombre, expediente);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return null;
    }
}
