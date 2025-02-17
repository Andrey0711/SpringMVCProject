package org.example.Controllers;

import jakarta.validation.Valid;
import org.example.Models.Book;
import org.example.Models.Person;
import org.example.Services.BookService;
import org.example.Services.PersonService;
import org.example.Validators.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final PersonService personService;
    private final BookService bookService;
    private final BookValidator bookValidator;
    @Autowired
    public BookController(PersonService personService, BookService bookService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.personService = personService;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String index(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "books_per_page", required = false, defaultValue = "4") int books_per_page,
                        @RequestParam(value = "sort_by_year", required = false, defaultValue = "false") boolean sortByYear,
                        Model model){
        model.addAttribute("book", bookService.findAll(page, books_per_page, sortByYear));
        System.out.println(bookService.findOne(3).get().isOverdue());
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
        if(bindingResult.hasErrors()) return "book/create_book";
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(Model model,
                               @ModelAttribute("personForChangeBookOwner") Person person1,
                               @PathVariable("id") int id){
        Optional<Book> tryGetBookById = bookService.findOne(id);
        tryGetBookById.ifPresent(element -> model.addAttribute("book", tryGetBookById.get()));

        Optional<Person> person = personService.findPersonByBookListId(id);
        person.ifPresentOrElse(element -> model.addAttribute("person", person.get()),
                () -> model.addAttribute("personList", personService.findAll()));
        return "/book/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editBookAction(@ModelAttribute("book") @Valid Book book,
                                 BindingResult bindingResult,
                                 @PathVariable("id") int id,
                                 Model model){
        book.setId(id);
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            Optional<Person> person = personService.findPersonByBookListId(id);
            person.ifPresentOrElse(element -> model.addAttribute("person", person.get()),
                    () -> {
                model.addAttribute("personList", personService.findAll());
                model.addAttribute("personForChangeBookOwner", person.get());
            });
            return "/book/edit";
        }
        bookService.update(book, id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/changereader")
    public String editChangeReader(@ModelAttribute("personToChangeBookOwner") Person person, @PathVariable("id") int id){
        Optional<Person> getPersonByChooseInForm = personService.findOne(person.getId());
        bookService.updatePersonInBook(getPersonByChooseInForm.get(), id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/edit/freereader")
    public String freeReader(@PathVariable("id") int id){
        bookService.removePersonById(id);
        return "redirect:/book";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("book") Book book,
                         @RequestParam(value = "name", required = false) String name,
                         Model model){
        Optional.ofNullable(name).ifPresentOrElse(bookList -> model.addAttribute("bookList", bookService.findBooksByNameContainingIgnoreCase(name)),
                () -> model.addAttribute("bookList", Collections.emptyList()));
        return "book/search";
    }

    @DeleteMapping("/{id}/edit")
    public String deleteBook(@PathVariable("id") int id){
        bookService.remove(id);
        return "redirect:/book";
    }
}
