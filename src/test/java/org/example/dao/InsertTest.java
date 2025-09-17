package org.example.dao;

import org.example.dao.exceptions.InvalidId;
import org.example.dao.exceptions.NoValidExpedient;
import org.example.dao.exceptions.NoValidName;
import org.example.models.Student;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class InsertTest {

    private static final String URL = "jdbc:mariadb://localhost:3306/StudentDAM";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Insert insert;

    @BeforeEach
    void setUp() {
        insert = new Insert();
    }

    @AfterEach
    void tearDown() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Student WHERE id >= 900")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cleanup failed: " + e.getMessage());
        }
    }

    @Test
    void testInsertNormalStudent() {
        Student student = new Student(900, "Test Normal", "EXP1");
        try {
            insert.insertStudent(student);
        } catch (NoValidName e) {
            System.out.println(e.getMessage());
        } catch (InvalidId e) {
            System.out.println(e.getMessage());
        } catch (NoValidExpedient e) {
            System.out.println(e.getMessage());
        }

        assertTrue(studentExists(900), "The student should have been inserted");
    }

    @Test
    void testInsertWithEmptyName() {
        Student student = new Student(901, "", "EXP2");

        NoValidName thrown = assertThrows(NoValidName.class, () -> {
            insert.insertStudent(student);
        });

        assertEquals("Invalid name: cannot be empty", thrown.getMessage());
    }

    @Test
    void testInsertWithDuplicateId() {
        Student student1 = new Student(902, "First", "EXP3");
        Student student2 = new Student(902, "Duplicate", "EXP4");

        try {
            insert.insertStudent(student1);
            insert.insertStudent(student2);
        } catch (NoValidName e) {
            System.out.println(e.getMessage());
        } catch (InvalidId e) {
            System.out.println(e.getMessage());
        } catch (NoValidExpedient e) {
            System.out.println(e.getMessage());
        }

        assertEquals(1, countStudentsById(902), "Only one student with that ID should exist.");
    }

    @Test
    void testInsertWithLongExpedient() {
        String longExp = "12345678901234567890";
        Student student = new Student(9034, "LongExp", longExp);

        NoValidExpedient thrown = assertThrows(NoValidExpedient.class, () -> {
            insert.insertStudent(student);
        });

        assertEquals("Expediente inválido: debe tener 4 caracteres o menos", thrown.getMessage());
    }


    private boolean studentExists(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Student WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            fail("SQL Exception: " + e.getMessage());
            return false;
        }
    }

    private int countStudentsById(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM Student WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            fail("SQL Exception: " + e.getMessage());
        }

        return 0;
    }
}
