package org.example.Services;

import jakarta.transaction.Transactional;
import org.example.Models.Mood;
import org.example.Models.Person;
import org.example.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findOne(int id){
        return personRepository.findById(id);
    }

    public void save(Person person){
        personRepository.save(person);
        person.setWas_created(LocalDateTime.now());
        person.setMood(Mood.HAPPY);
    }

    public void update(int id, Person person){
        person.setId(id);
        personRepository.save(person);
    }

    public void remove(int id){
        personRepository.deleteById(id);
    }

    public Optional<Person> findPersonByBookListId(int id){
        return personRepository.findPersonByBookListId(id);
    }

    public Optional<Person> findPersonByFullname(String fullname){
        return personRepository.findPersonByFullname(fullname);
    }
}
