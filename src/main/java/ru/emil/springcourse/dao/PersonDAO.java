package ru.emil.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.emil.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int id = 4;

    public List<Person> getPeople(){
        return jdbcTemplate.query("SELECT * FROM person",new PersonMapper());
    }

    public Person show(int id_){
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?",
                new Object[]{id_},new PersonMapper()).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person VALUES(?,?,?,?)", ++id,person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id_, Person person){
        jdbcTemplate.update("UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?",
                person.getName(),person.getAge(),person.getEmail(), id_);
    }

    public void delete(int id_) {
        Person.setTmpName(show(id_).getName());
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id_);
        --id;
    }
}
