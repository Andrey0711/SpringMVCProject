package org.example.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    @Pattern(regexp = "[A-Z0-9]+.*", message = "You can't enter incorrect name. For example: The catcher in the rye")
    @Column(name = "name")
    private String name;
    @Pattern(regexp = "[A-Z]+[a-z]+ [A-Z]+[a-z]+", message = "You can't enter incorrect author. For example: Ivan Ivanov")
    @Column(name = "author")
    private String author;
    @Range(min = 1000, max = 2025, message = "You can't enter incorrect year of production. For example: 2000")
    @Column(name = "year_of_production")
    private int year_of_production;

    public Book(String name, String author, int year_of_production) {
        this.name = name;
        this.author = author;
        this.year_of_production = year_of_production;
    }
    public Book(){}
    public int getId() {
        return id;
    }

    public void setId(int book_id) {
        this.id = book_id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear_of_production() {
        return year_of_production;
    }

    public void setYear_of_production(int year_of_production) {
        this.year_of_production = year_of_production;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + id +
                ", person=" + person +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year_of_production=" + year_of_production +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && year_of_production == book.year_of_production && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, year_of_production);
    }
}
