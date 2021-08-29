package org.techtask.addressbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techtask.addressbook.model.Book;
import org.techtask.addressbook.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository repository;

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(Long bookId) {
        return repository.getById(bookId);
    }

    public Book create(Book book) {
        return repository.save(book);
    }


}
