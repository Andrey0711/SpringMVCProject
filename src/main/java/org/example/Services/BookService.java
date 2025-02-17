package org.example.Services;

import org.example.Models.Book;
import org.example.Models.Person;
import org.example.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(int page, int books_per_page, boolean sortByYear){

        return sortByYear
                ? bookRepository.findAll(PageRequest.of(page, books_per_page, Sort.by("yearOfProduction"))).getContent()
                : bookRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
    }

    public Optional<Book> findOne(int id){
        return bookRepository.findById(id);
    }

    public void update(Book book, int id){
        book.setId(id);
        bookRepository.save(book);
    }

    public void save(Book book){
        bookRepository.save(book);
        book.setWas_created(LocalDateTime.now());
    }

    public void remove(int id){
        bookRepository.deleteById(id);
    }

    public Optional<Book> findBookByName(String name){
        return bookRepository.findBookByName(name);
    }

    public Optional<List<Book>> findBooksByPersonId(int id){
        return bookRepository.findBooksByPersonId(id);
    }

    public void removePersonById(int id){
        bookRepository.removePersonById(id);
    }

    public void updatePersonInBook(Person person, int id){
        bookRepository.updatePersonInBook(person, id);
    }

    public List<Book> findBooksByNameContainingIgnoreCase(String name){
        return !name.isEmpty() ? bookRepository.findBooksByNameContainingIgnoreCase(name) : Collections.emptyList();
    }
}
