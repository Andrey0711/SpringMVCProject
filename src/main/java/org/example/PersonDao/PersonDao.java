package org.example.PersonDao;

import org.example.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id >= 1", new BeanPropertyRowMapper<>(Person.class));
    }

    public void createNewPerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(fullname, birthyear) VALUES(?, ?)", person.getFullname(), person.getBirthYear());
    }

    public Optional<Person> getPersonByFullname(String fullname){
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullname=?", new BeanPropertyRowMapper<>(Person.class), fullname).stream().findAny();
    }
    public Optional<Person> getPersonById(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
    }

    public void updatePerson(Person person){
        jdbcTemplate.update("UPDATE Person SET fullname=?, birthyear=? WHERE person_id=?", person.getFullname(), person.getBirthYear(), person.getPerson_id());
    }
    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }
}
