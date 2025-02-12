package org.example.BookDao;

import org.example.Models.Book;
import org.example.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void createBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(name, author, year_of_production) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear_of_production());
    }
    public Book getBookById(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new BeanPropertyRowMapper<>(Book.class), id).stream().findAny().orElse(null);
    }
    public Optional<Book> getBookByName(String name){
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?", new BeanPropertyRowMapper<>(Book.class), name).stream().findAny();
    }
    public void deleteBook(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
    public void updateBook(Book book, int id){
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_production=? WHERE book_id=?", book.getName(), book.getAuthor(), book.getYear_of_production(), id);
    }
    public void changePersonInBook(Book book, int id){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", book.getPerson_id(), id);
    }
    public void freeBook(int id){
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", id);
    }
    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }
    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Person.person_id = Book.person_id WHERE Book.book_id=?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
    }
}
