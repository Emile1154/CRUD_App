package ru.emil.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.emil.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int id;
    private List<Person> people;

    {
        people = new ArrayList<Person>();
        people.add(new Person(++id, "Tom"));
        people.add(new Person(++id, "Jack"));
        people.add(new Person(++id, "Julia"));
        people.add(new Person(++id, "Elon"));
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person show(int id){
        return people.stream().filter(people -> people.getId() == id).findAny().orElse(null);
    }
}
