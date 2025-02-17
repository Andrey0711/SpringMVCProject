package org.example.Dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.Models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDao {

//    private final EntityManager entityManager;
//
//    @Autowired
//    public PersonDao(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Transactional
//    public void checkingNPlusOne(){
//        Session session = entityManager.unwrap(Session.class);
////        List<Person> personList = session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
////        for (Person person : personList){
////            System.out.println("Person name: " + person.getFullname() + "/" + "Person books: " + person.getBookList());
////        }
//        List<Person> personList = session.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.bookList").getResultList();
//
//        for (Person person : personList){
//            System.out.println("Person name: " + person.getFullname() + "/" + "Person bookList: " + person.getBookList());
//        }
//    }
}
