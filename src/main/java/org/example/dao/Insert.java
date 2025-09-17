package org.example.dao;

import org.example.dao.exceptions.InvalidId;
import org.example.dao.exceptions.NoValidExpedient;
import org.example.dao.exceptions.NoValidName;
import org.example.models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    private final static String URL = "jdbc:mariadb://localhost:3306/StudentDAM";
    private final static String USER = "root";
    private final static String PASSWORD = "";

    public void insertStudent(Student student) throws NoValidName, InvalidId, NoValidExpedient {
        if (student.getId() <= 0) {
            throw new InvalidId("ID inv: < 0");
        }
        if (student.getName() == null || student.getName().isBlank()) {
            throw new NoValidName("Invalid name: cannot be empty");
        }
        if (student.getExpedient() == null || student.getExpedient().length() > 4) {
            throw new NoValidExpedient("Expedient not valid: > 4");
        }

        String sql = """
                INSERT INTO Student (name,id,expediente)
                VALUES (?,?,?)""";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getId());
            ps.setString(3, student.getExpedient());

            int rows = ps.executeUpdate();

            System.out.println("Insert " + rows + " rows");

            connection.commit();

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
