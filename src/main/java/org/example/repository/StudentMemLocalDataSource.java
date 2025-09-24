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

        for (Student stu : allStudents.values()) {
            int id = stu.getId();
            String name = stu.getName();

            for (String key : allStudents.keySet()) {
                Student newStu = new Student(id, name, key);
                listStudent.add(newStu);
            }
        }
        return listStudent;
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
