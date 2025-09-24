package org.example.repository;

import org.example.models.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentMemLocalDataSource implements StudentRepository {

    HashMap<String, Student> allStudents = new HashMap<>();
    List<Student> listStudent = new ArrayList<>();

    public void addStudent(Student student) {
        allStudents.put(student.getExpedient(), student);
    }

    @Override
    public List<Student> allStudents() {
        return new ArrayList<>(allStudents.values());
    }

    @Override
    public Student searchStudent(String name) {
        for (Student stu : allStudents.values()) {
            if (stu.getName().equals(name)) {
                return stu;
            }
        }
        return null;
    }

    @Override
    public void deleteStudent(Student student) {
        allStudents.remove(student.getExpedient());
    }

    @Override
    public void updateStudent(String name, Student student) {
        if (allStudents.containsKey(student.getExpedient())) {
            student.setName(name);
        }
    }
}
