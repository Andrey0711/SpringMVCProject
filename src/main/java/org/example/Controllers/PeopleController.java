package org.example.Controllers;

import jakarta.validation.Valid;
import org.example.BookDao.BookDao;
import org.example.Models.Person;
import org.example.PersonDao.PersonDao;
import org.example.Validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PeopleController {
    private final PersonDao personDao;
    @Autowired
    private final BookDao bookDao;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDao personDao, BookDao bookDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.bookDao = bookDao;
        this.personValidator = personValidator;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("person", personDao.index());
        return "person/index";
    }
    @GetMapping("/new")
    public String createNewReaderForm(@ModelAttribute("person") Person person){
        return "person/create_reader";
    }
    @PostMapping("/new")
    public String createNewReaderAction(@ModelAttribute("person") @Valid Person person,
                                        BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "person/create_reader";
        }
        personDao.createNewPerson(person);
        return "redirect:/person";
    }
    @GetMapping("/{id}/edit")
    public String editReaderForm(@PathVariable("id") int id, Model model){
        if(personDao.getPersonById(id).isPresent()){
            model.addAttribute("person", personDao.getPersonById(id).get());
        }
        model.addAttribute("books", bookDao.getBooksByPersonId(id));
        return "/person/edit";
    }
    @PatchMapping("/{id}/edit")
    public String editReaderAction(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id, Model model){
        person.setPerson_id(id);
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            if(personDao.getPersonById(id).isPresent()){
                model.addAttribute("person", person);
                model.addAttribute("books", bookDao.getBooksByPersonId(id));
            }
            return "/person/edit";
        }
        personDao.updatePerson(person);
        return "redirect:/person";
    }
    @DeleteMapping("/{id}/edit")
    public String deleteReaderAction(@PathVariable("id") int id){
        personDao.deletePerson(id);
        return "redirect:/person";
    }

}
