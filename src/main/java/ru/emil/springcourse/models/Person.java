package ru.emil.springcourse.models;

import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @Size(min = 2, max = 12, message = "name length be between 2 and 12 characters")
    private String name;

    @Email(regexp = ".+@.+\\..+",message = "not valid email address")
    private String email;

    @Min(value = 1, message = "age should be greater than 0")
    private int age;

    private static String tmpName;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
