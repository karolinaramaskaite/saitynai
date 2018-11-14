package com.saitynai.project.saitynai.controllers;

import com.saitynai.project.saitynai.model.Book;
import com.saitynai.project.saitynai.requests.BookRequest;
import com.saitynai.project.saitynai.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/books", produces = "application/json")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(final BookService service) {
        this.service = service;
    }

    @PostMapping(headers = {"Authorization"})
    public ResponseEntity<Book> insertBook(@RequestBody BookRequest request) {
        return new ResponseEntity<>(service.insertBook(request), HttpStatus.CREATED);
    }

    @GetMapping(headers = {"Authorization"})
    public List<Book> getAllBooks() {
        return service.getBooks();
    }

    @GetMapping(path = "{id}", headers = {"Authorization"})
    public Book getBook(@PathVariable String id) {
        return service.getBookById(id);
    }

    @PatchMapping(path = "{id}", headers = {"Authorization"})
    public Book updateBook(@PathVariable String id, @RequestBody BookRequest request) {
        return service.modifyBook(id, request);
    }

    @DeleteMapping(path = "{id}", headers = {"Authorization"})
    public ResponseEntity deleteBook(@PathVariable String id) {
        service.deleteBookById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
