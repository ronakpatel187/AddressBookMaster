package org.techtask.addressbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techtask.addressbook.exception.EntityNotFoundException;
import org.techtask.addressbook.model.Book;
import org.techtask.addressbook.model.Contact;
import org.techtask.addressbook.repository.ContactRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    ContactRepository repository;

    @Autowired
    BookService bookService;

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact create(Contact contact) {
        //System.out.println("-------------------------------" + contact.getBook().getId());
        Book book = bookService.getById(contact.getBook().getId().longValue());
        contact.setBook(book);
        System.out.println(contact);
        return repository.save(contact);
    }

    public void deleteById(long id) {
        repository.deleteById(Long.valueOf(id));
    }

    public Contact getById(long id) {
        Optional<Contact> optionalContact = repository.findById(Long.valueOf(id));
        if (optionalContact.isPresent()) {
            return optionalContact.get();
        }

        throw new EntityNotFoundException("Contact not found");
    }

    public List<Contact> getByBookId(long bookId) {
        Book book = bookService.getById(bookId);
        if (book == null)
            throw new EntityNotFoundException("Book not found");
        return repository.findByBookId(bookId);
    }

    public <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t ->
        {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());

            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }
}
