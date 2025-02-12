package org.example.Models;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

public class Book {
    private int book_id;
    private Integer person_id;
    @Pattern(regexp = "[A-Z0-9]+.*", message = "You can't enter incorrect name. For example: The catcher in the rye")
    private String name;
    @Pattern(regexp = "[A-Z]+[a-z]+ [A-Z]+[a-z]+", message = "You can't enter incorrect author. For example: Ivan Ivanov")
    private String author;
    @Range(min = 1000, max = 2025, message = "You can't enter incorrect year of production. For example: 2000")
    private int year_of_production;

    public Book(int book_id, Integer person_id, String name, String author, int year_of_production) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.name = name;
        this.author = author;
        this.year_of_production = year_of_production;
    }
    public Book(){}
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
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
                "book_id=" + book_id +
                ", person_id=" + person_id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year_of_production=" + year_of_production +
                '}';
    }
}
