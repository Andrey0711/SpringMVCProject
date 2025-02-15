package org.example.Controllers;

import jakarta.validation.Valid;
import org.example.BookDao.BookDao;
import org.example.Models.Book;
import org.example.Models.Person;
import org.example.PersonDao.PersonDao;
import org.example.Services.BookService;
import org.example.Services.PersonService;
import org.example.Validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
@RequestMapping("/person")
public class PeopleController {
    private final BookService bookService;
    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(BookService bookService, PersonService personService, PersonValidator personValidator) {
        this.bookService = bookService;
        this.personService = personService;
        this.personValidator = personValidator;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("person", personService.findAll());
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
        if(bindingResult.hasErrors()) return "person/create_reader";
        personService.save(person);
        return "redirect:/person";
    }
    @GetMapping("/{id}/edit")
    public String editReaderForm(@PathVariable("id") int id, Model model){
        Optional<Person> tryGetPersonById = personService.findOne(id);
        Optional<List<Book>> tryGetBooksByPersonId = bookService.findBooksByPersonId(id);
        tryGetPersonById.ifPresent(element -> model.addAttribute("person", tryGetPersonById.get()));
        tryGetBooksByPersonId.ifPresent(element -> model.addAttribute("books", tryGetBooksByPersonId.get()));
        return "/person/edit";
    }
    @PatchMapping("/{id}/edit")
    public String editReaderAction(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id, Model model){
        person.setId(id);
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            Optional<List<Book>> tryGetBooksByPersonId = bookService.findBooksByPersonId(id);
            tryGetBooksByPersonId.ifPresent(books -> model.addAttribute("books", tryGetBooksByPersonId.get()));
            return "/person/edit";
        }
        personService.update(id, person);
        return "redirect:/person";
    }
    @DeleteMapping("/{id}/edit")
    public String deleteReaderAction(@PathVariable("id") int id){
        personService.remove(id);
        return "redirect:/person";
    }

}
