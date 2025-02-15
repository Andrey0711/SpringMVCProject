package org.example.BookDao;

import jakarta.transaction.Transactional;
import org.example.Models.Book;
import org.example.Models.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDao(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    private Session getSessionFactory(){
//        return sessionFactory.getCurrentSession();
//    }
//
//    @Transactional
//    public List<Book> index(){
//        Session session = getSessionFactory();
//        return session.createQuery("SELECT b from Book b", Book.class).getResultList();
//    }
//
//    @Transactional
//    public void createBook(Book book){
//        Session session = getSessionFactory();
//        session.persist(book);
//    }
//
//    @Transactional
//    public Optional<Book> getBookById(int id){
//        Session session = getSessionFactory();
//        return Optional.ofNullable(session.get(Book.class, id));
//    }
//
//    @Transactional
//    public Optional<Book> getBookByName(String name){
//        Session session = getSessionFactory();
//        return session.createQuery("SELECT b FROM Book b WHERE b.name=:name", Book.class)
//                .setParameter("name", name)
//                .uniqueResultOptional();
//    }
//
//    @Transactional
//    public void deleteBook(int id){
//        Session session = getSessionFactory();
//        Book bookObj = session.get(Book.class, id);
//        session.remove(bookObj);
//    }
//
//    @Transactional
//    public void updateBook(Book book, int id){
//        Session session = getSessionFactory();
//
//        Book bookObj = session.get(Book.class, id);
//        bookObj.setName(book.getName());
//        bookObj.setAuthor(book.getAuthor());
//        bookObj.setYear_of_production(book.getYear_of_production());
//    }
//
//    @Transactional
//    public void changePersonInBook(Person person, int id){
//        Session session = getSessionFactory();
//        Book bookObj = session.get(Book.class, id);
//        Person personObj = session.get(Person.class, person.getId());
//        bookObj.setPerson(personObj);
//        personObj.getBookList().add(bookObj);
//    }
//
//    @Transactional
//    public void freeBook(int id){
//        Session session = getSessionFactory();
//        Book bookObj = session.get(Book.class, id);
//        bookObj.getPerson().getBookList().remove(bookObj);
//        bookObj.setPerson(null);
//    }
//
//    @Transactional
//    public Optional<List<Book>> getBooksByPersonId(int id){
//        Session session = getSessionFactory();
//        Person personObj = session.get(Person.class, id);
//        Hibernate.initialize(personObj.getBookList());
//        return Optional.ofNullable(personObj.getBookList());
//    }
//
//    @Transactional
//    public Optional<Person> getBookOwner(int id){
//        Session session = getSessionFactory();
//        Book bookObj = session.get(Book.class, id);
//        return Optional.ofNullable(bookObj.getPerson());
//    }
}
