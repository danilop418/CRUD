package org.example.models;

public class Student {
    private int id;
    private String name;
    private String expedient;

    public Student(int id, String name, String expedient) {
        this.id = id;
        this.name = name;
        this.expedient = expedient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpedient(String expedient) {
        this.expedient = expedient;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExpedient() {
        return expedient;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expedient='" + expedient + '\'' +
                '}';
    }
}
