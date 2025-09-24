package org.example.UI;

import org.example.models.Student;
import org.example.repository.StudentRepository;
import org.example.repository.StudentMemLocalDataSource;

import java.util.Scanner;

public class Ui {
    private StudentRepository repository;

    public Ui(StudentRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {

        StudentRepository repo = new StudentMemLocalDataSource();

        Ui app = new Ui(repo);
        app.runMenu();
    }

    private void runMenu() {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("""
                    MENU:
                    1- Add Student
                    2- Change name
                    3- Search Student
                    4- Delete Student
                    5- Show all Students
                    6- Exit
                    """);
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter Student ID");
                    int id = sc.nextInt();
                    System.out.println("Enter Student Name");
                    String name = sc.next();
                    System.out.println("Enter Student Expedient");
                    String expedient = sc.next();

                    repository.addStudent(new Student(id, name, expedient));
                    break;

                case 2:
                    System.out.println("Enter Student name");
                    String oldName = sc.next();
                    System.out.println("Enter Student new name");
                    String newName = sc.next();

                    Student student = repository.searchStudent(oldName);
                    if (student != null) {
                        repository.updateStudent(newName, student);
                    }
                    break;

                case 3:
                    System.out.println("Enter Student name for search");
                    String searchName = sc.next();
                    System.out.println(repository.searchStudent(searchName));
                    break;

                case 4:
                    System.out.println("Enter Student name for delete");
                    String deleteName = sc.next();
                    Student toDelete = repository.searchStudent(deleteName);
                    if (toDelete != null) {
                        repository.deleteStudent(toDelete);
                    }
                    break;

                case 5:
                    System.out.println(repository.allStudents());
                    break;

                case 6:
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Invalid input!");
            }
        } while (option != 6);
    }
}