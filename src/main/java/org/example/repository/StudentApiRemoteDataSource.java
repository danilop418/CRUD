package org.example.repository;

import org.example.dao.Delete;
import org.example.dao.Insert;
import org.example.dao.Select;
import org.example.dao.Update;
import org.example.dao.exceptions.InvalidId;
import org.example.dao.exceptions.NoValidExpedient;
import org.example.dao.exceptions.NoValidName;
import org.example.models.Student;

import java.util.List;

public class StudentApiRemoteDataSource implements StudentRepository {

    Insert insert = new Insert();
    Update update = new Update();
    Select select = new Select();
    Delete delete = new Delete();

    @Override
    public void addStudent(Student student) {
        try {
            insert.insertStudent(student);
        } catch (NoValidName | InvalidId | NoValidExpedient e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> allStudents() {
        return select.allSaveStudents();
    }

    @Override
    public Student searchStudent(String name) {
        try {
            return select.searchForName(name);
        } catch (NoValidName e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteStudent(Student student) {
        try {
            delete.delete(student);
        } catch (NoValidName e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateStudent(String newName, Student student) {
        update.updateStudent(student, newName);
    }
}
