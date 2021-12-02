package ru.emil.springcourse.models;

public class Person {
    private int id;
    private String name;
    private String surname;
    private String email;
    private static String tmpName;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getTmpName() {
        return tmpName;
    }

    public static void setTmpName(String tmpName) {
        Person.tmpName = tmpName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
