package org.example.dao;

import org.example.dao.exceptions.NoValidName;
import org.example.models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    private final String URL = "jdbc:mariadb://localhost:3306/StudentDAM";
    private final String USER = "root";
    private final String PASSWORD = "";

    public void delete(Student student) throws NoValidName {
        String sql = "DELETE FROM student WHERE name = ? ";

        if (student.getName() == null) {
            throw new NoValidName("Invalid name: cannot be empty");
        }

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connection ok");

            PreparedStatement ps = conexion.prepareStatement(sql);
            int i = 1;

            ps.setString(i, student.getName());

            int rows = ps.executeUpdate();

            System.out.println("Delete" + rows + " rows");

            conexion.commit();

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
