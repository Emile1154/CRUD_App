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
        people.add(new Person(++id, "Tom", 20, "test@mail.ru"));
        people.add(new Person(++id, "Jack", 25 , "Jack@mail.ru"));
        people.add(new Person(++id, "Julia", 19, "email@email.ru"));
        people.add(new Person(++id, "Elon", 45, "tesla@gmail.com"));
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person show(int id){
        return people.stream().filter(people -> people.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++id);
        people.add(person);
    }

    public void update(int id, Person person){
        people.set(id-1,person);
    }

    public void delete(int id_) {
        id = id_;
        Person.setTmpName(people.get(id_- 1).getName());
        people.removeIf(people -> people.getId() == id_);
        for(Person person1 : people){
            if(person1.getId() > id_){
                person1.setId(person1.getId()-1);
            }
        }
    }
}
