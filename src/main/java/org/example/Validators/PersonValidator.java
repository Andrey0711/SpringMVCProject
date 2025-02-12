package org.example.Validators;

import org.example.Models.Person;
import org.example.PersonDao.PersonDao;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDao personDao;

    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personDao.getPersonByFullname(person.getFullname()).isPresent() && (person.getPerson_id() != personDao.getPersonByFullname(person.getFullname()).get().getPerson_id())) {
            errors.rejectValue("fullname", "", "This full name was already taken");
        }
    }
}
