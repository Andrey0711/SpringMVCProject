package org.example.Repositories;

import jakarta.validation.constraints.Pattern;
import org.example.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonByBookListId(int id);

    Optional<Person> findPersonByFullname(String name);
}
