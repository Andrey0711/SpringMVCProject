package org.example.Repositories;

import org.example.Models.Book;
import org.example.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<List<Book>> findBooksByPersonId(int id);
    Optional<Book> findBookByName(String name);
    void removePersonById(int id);

    @Query("update Book b set b.person = :person where b.id = :id")
    @Modifying
    void updatePersonInBook(@Param("person") Person person, @Param("id") int bookId);

}
