package org.example.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

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
    @Range(min=1900, max=2025, message = "You can't enter incorrect birth year")
    @Column(name = "birthYear")
    private int birthYear;
    @OneToMany(mappedBy = "person")
    private List<Book> bookList;

    public Person(String fullname, int birthYear) {
        this.fullname = fullname;
        this.birthYear = birthYear;
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

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Book> getBookList(){
        return bookList;
    }
    public void setBookList(List<Book> bookList){
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + id +
                ", fullname='" + fullname + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && birthYear == person.birthYear && Objects.equals(fullname, person.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, birthYear);
    }
}
