package org.example.UI;

import org.example.dao.Delete;
import org.example.dao.Insert;
import org.example.dao.Select;
import org.example.dao.Update;
import org.example.dao.exceptions.InvalidId;
import org.example.dao.exceptions.NoValidExpedient;
import org.example.dao.exceptions.NoValidName;
import org.example.models.Student;

import java.util.Scanner;

public class Ui {
    static void main(String[] args) {
        Insert insert = new Insert();
        Update update = new Update();
        Delete delete = new Delete();
        Select select = new Select();
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

                    Student student = new Student(id, name, expedient);

                    try {
                        insert.insertStudent(student);
                    } catch (NoValidName e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidId e) {
                        System.out.println(e.getMessage());
                    } catch (NoValidExpedient e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enter Student name");
                    String nameForChange = sc.next();

                    System.out.println("Enter Student new name");
                    String newName = sc.next();

                    Student studentOld = null;
                    try {
                        studentOld = select.searchForName(nameForChange);
                    } catch (NoValidName e) {
                        System.out.println(e.getMessage());
                    }

                    update.updateStudent(studentOld, newName);
                    break;

                case 3:
                    System.out.println("Enter Student name for search");
                    String nameForSearch = sc.next();

                    try {
                        System.out.println(select.searchForName(nameForSearch));
                    } catch (NoValidName e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Enter Student for delete");
                    String nameForDelete = sc.next();

                    Student studentForDelete = null;
                    try {
                        studentForDelete = select.searchForName(nameForDelete);
                    } catch (NoValidName e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        delete.delete(studentForDelete);
                    } catch (NoValidName e) {
                        System.out.println(e.getMessage());
                    }
                case 5:
                    System.out.println(select.allSaveStudents());
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
