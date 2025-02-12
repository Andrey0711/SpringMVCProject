package org.example.Validators;

import org.example.BookDao.BookDao;
import org.example.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final BookDao bookDao;

    @Autowired
    public BookValidator(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if(bookDao.getBookByName(book.getName()).isPresent() && bookDao.getBookByName(book.getName()).get().getBook_id() != book.getBook_id()){
            errors.rejectValue("name", "", "This book is already exists");
        }
    }
}
