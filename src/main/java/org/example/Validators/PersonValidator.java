package org.example.Validators;

import org.example.Models.Person;
import org.example.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
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
        boolean isMatch = String.valueOf(person.getDate_of_birth()).matches("(19[0-9]{2}|200[0-9]+|201[0-9]+|202[0-5]+)-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[0-9]|3[0-1])");
        if(!isMatch){
            errors.rejectValue("date_of_birth", "");
        }
    }
}
