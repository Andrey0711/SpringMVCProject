package org.example.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class Person {
    private int person_id;
    @Pattern(regexp = "[A-Z]+[a-z]+ [A-Z]+[a-z]+ [A-Z]+[a-z]+", message = "You can't enter incorrect full name. For example: Ivanov Ivan Ivanovich")
    private String fullname;
    @Range(min=1900, max=2025, message = "You can't enter incorrect birth year")
    private int birthYear;

    public Person(int person_id, String fullname, int birthYear) {
        this.person_id = person_id;
        this.fullname = fullname;
        this.birthYear = birthYear;
    }
    public Person(){}
    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", fullname='" + fullname + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
