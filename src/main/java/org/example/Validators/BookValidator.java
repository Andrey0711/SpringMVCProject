package org.example.Validators;

import org.example.Models.Book;
import org.example.Models.Person;
import org.example.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class BookValidator implements Validator {

    private final BookService bookService;

    @Autowired
    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        Book bookObj = null;
        Optional<Book> tryGetBookByName = bookService.findBookByName(book.getName());
        if(tryGetBookByName.isPresent()){
            bookObj = tryGetBookByName.get();
        }
        if(bookObj != null && (book.getId() != bookObj.getId())) {
            errors.rejectValue("name", "", "This book is already exists");
        }
    }
}
