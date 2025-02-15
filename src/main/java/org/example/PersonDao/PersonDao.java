package org.example.PersonDao;

import jakarta.transaction.Transactional;
import org.example.Models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDao(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    private Session getSessionFactory(){
//        return sessionFactory.getCurrentSession();
//    }
//
//    @Transactional
//    public List<Person> index(){
//        Session session = getSessionFactory();
//        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
//    }
//
//    @Transactional
//    public void createNewPerson(Person person){
//        Session session = getSessionFactory();
//        Person personObj = new Person(person.getFullname(), person.getBirthYear());
//        session.persist(personObj);
//    }
//
//    @Transactional
//    public Optional<Person> getPersonByFullname(String fullname){
//        Session session = getSessionFactory();
//        return session.createQuery("SELECT p FROM Person p WHERE p.fullname=:fullname", Person.class)
//                .setParameter("fullname", fullname)
//                .uniqueResultOptional();
//    }
//
//    @Transactional
//    public Optional<Person> getPersonById(int id){
//        Session session = getSessionFactory();
//        return Optional.ofNullable(session.get(Person.class, id));
//    }
//
//    @Transactional
//    public void updatePerson(Person person){
//        Session session = getSessionFactory();
//        Person personObj = session.get(Person.class, person.getId());
//        personObj.setFullname(person.getFullname());
//        personObj.setBirthYear(person.getBirthYear());
//    }
//
//    @Transactional
//    public void deletePerson(int id){
//        Session session = getSessionFactory();
//        session.remove(session.get(Person.class, id));
//    }
}
