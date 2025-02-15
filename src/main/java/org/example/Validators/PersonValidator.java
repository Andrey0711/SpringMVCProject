package org.example.Validators;

import org.example.Models.Person;
import org.example.PersonDao.PersonDao;
import org.example.Services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    public PersonValidator(PersonDao personDao, PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> getPersonByFullname = personService.findPersonByFullname(person.getFullname());
        Person savePerson = null;
        if(getPersonByFullname.isPresent()){
            savePerson = getPersonByFullname.get();
        }
        if(savePerson != null && (person.getId() != savePerson.getId())) {
            errors.rejectValue("fullname", "", "This full name was already taken");
        }
    }
}
