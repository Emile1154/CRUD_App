package ru.emil.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.emil.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int id = 4;
    private static final String url_db = "jdbc:mysql://localhost:3306/test_db";
    private static final String userName  = "postgres";
    private static final String password  = "postgres";
    private static final String const_request = "SELECT * FROM person";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url_db, userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPeople(){
        List<Person> people = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(const_request);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return people;
    }

    public Person show(int id_){
        try{
            PreparedStatement statement = connection.prepareStatement( "SELECT * FROM person WHERE id = ?");
            statement.setInt(1,id_);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            Person founded_person = new Person();
            founded_person.setId(resultSet.getInt("id"));
            founded_person.setName(resultSet.getString("name"));
            founded_person.setAge(resultSet.getInt("age"));
            founded_person.setEmail(resultSet.getString("email"));
            return founded_person;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void save(Person person){
        try {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO person VALUES(?,?,?,?)");
            statement.setInt(1,++id);
            statement.setString(2,person.getName());
            statement.setInt(3,person.getAge());
            statement.setString(4,person.getEmail());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void update(int id_, Person person){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?");
            statement.setString(1,person.getName());
            statement.setInt(2,person.getAge());
            statement.setString(3,person.getEmail());
            statement.setInt(4, id_);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id_) {
        try{
            Person.setTmpName(show(id_).getName());
            PreparedStatement statement_delete = connection.prepareStatement("DELETE FROM person WHERE id = ?");
            statement_delete.setInt(1,id_);
            statement_delete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        --id;
    }
}
