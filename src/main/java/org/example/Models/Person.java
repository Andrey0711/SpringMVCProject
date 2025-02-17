package org.example.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "[A-Z]+[a-z]+ [A-Z]+[a-z]+ [A-Z]+[a-z]+", message = "You can't enter incorrect full name. For example: Ivanov Ivan Ivanovich")
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date_of_birth;
    @Column(name = "was_created")
    private LocalDateTime was_created;
    @OneToMany(mappedBy = "person")
    private List<Book> bookList;
    @Column(name = "mood")
    @Enumerated(EnumType.ORDINAL)
    private Mood mood;

    public Person(String fullname, LocalDate date_of_birth) {
        this.fullname = fullname;
        this.date_of_birth = date_of_birth;
    }

    public Person(){}
    public int getId() {
        return id;
    }

    public void setId(int person_id) {
        this.id = person_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public List<Book> getBookList(){
        return bookList;
    }
    public void setBookList(List<Book> bookList){
        this.bookList = bookList;
    }

    public LocalDateTime getWas_created() {
        return was_created;
    }

    public void setWas_created(LocalDateTime was_created) {
        this.was_created = was_created;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + id +
                ", fullname='" + fullname + '\'' +
                ", birthYear=" + date_of_birth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && date_of_birth == person.date_of_birth && Objects.equals(fullname, person.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, date_of_birth);
    }
}
