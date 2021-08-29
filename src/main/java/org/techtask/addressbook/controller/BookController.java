package org.techtask.addressbook.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.techtask.addressbook.controller.dto.BookDTO;
import org.techtask.addressbook.model.Book;
import org.techtask.addressbook.service.BookService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    protected ModelMapper modelMapper;

    @GetMapping
    public List<BookDTO> get() {
        List<Book> books = bookService.getAll();
        System.out.println("Total books found: " + books.size());
        return books.stream().map(book -> convertToDTO(book)).collect(Collectors.toList());
    }

    /*@PostMapping
    public BookDTO create(@RequestBody BookDTO book) {
        return convertToDTO(bookService.create(convertToVo(book)));
    }*/

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookDTO book){
        Book newBook = bookService.create(convertToVo(book));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/book")
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(location).body(newBook);
    }

    private BookDTO convertToDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    private Book convertToVo(BookDTO book) {
        return modelMapper.map(book, Book.class);
    }
}
