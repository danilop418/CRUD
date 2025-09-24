package org.example.repository;

import org.example.models.Student;

import java.util.List;

public interface StudentRepository {

    public void addStudent(Student student);

    List<Student> allStudents();

    Student searchStudent(String name);

    void deleteStudent(Student student);

    void updateStudent(String newName, Student student);
}
