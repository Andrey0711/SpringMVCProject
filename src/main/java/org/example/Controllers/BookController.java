package org.example.Controllers;

import jakarta.validation.Valid;
import org.example.Models.Book;
import org.example.BookDao.BookDao;
import org.example.Models.Person;
import org.example.PersonDao.PersonDao;
import org.example.Validators.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.InvalidEndpointRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;
    private final BookValidator bookValidator;
    @Autowired
    public BookController(BookDao bookDao, BookValidator bookValidator, PersonDao personDao) {
        this.bookDao = bookDao;
        this.bookValidator = bookValidator;
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model) throws InvalidEndpointRequestException{
        model.addAttribute("book", bookDao.index());
        return "book/index";
    }

    @GetMapping("/new")
    public String createBookForm(@ModelAttribute("book") Book book){
        return "book/create_book";
    }
    @PostMapping("/new")
    public String createBookAction(@ModelAttribute("book") @Valid Book book,
                                   BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors()){
            return "book/create_book";
        }
        bookDao.createBook(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDao.getBookById(id));
        Optional<Person> person = bookDao.getBookOwner(id);
        if(person.isPresent()){
            model.addAttribute("person", person.get());
        } else {
            model.addAttribute("personList", personDao.index());
        }
        return "/book/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editBookAction(@ModelAttribute("book") @Valid Book book,
                                 BindingResult bindingResult,
                                 @PathVariable("id") int id,
                                 Model model){
        book.setBook_id(id);
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            Optional<Person> person = bookDao.getBookOwner(id);
            if(person.isPresent()){
                model.addAttribute("person", person.get());
            } else {
                model.addAttribute("personList", personDao.index());
            }
            return "/book/edit";
        }
        bookDao.updateBook(book, id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/changereader")
    public String editChangeReader(@ModelAttribute("book") Book book, @PathVariable("id") int id){
        bookDao.changePersonInBook(book, id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/edit/freereader")
    public String freeReader(@PathVariable("id") int id){
        bookDao.freeBook(id);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}/edit")
    public String deleteBook(@PathVariable("id") int id){
        bookDao.deleteBook(id);
        return "redirect:/book";
    }
}
