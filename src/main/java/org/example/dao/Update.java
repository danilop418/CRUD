package org.example.dao;

import org.example.models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    private final String URL = "jdbc:mariadb://localhost:3306/StudentDAM";
    private final String USER = "root";
    private final String PASSWORD = "";

    public void updateStudent(Student student, String newName) {
        String sql = """
                UPDATE student
                SET name = ?
                WHERE name = ?""";

        if (student.getName() == null || newName == null ) {
            throw new IllegalArgumentException("Invalid name: cannot be empty");
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setString(2, student.getName());

            int rows = ps.executeUpdate();

            System.out.println("Update " + rows + " rows");

            connection.commit();

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
