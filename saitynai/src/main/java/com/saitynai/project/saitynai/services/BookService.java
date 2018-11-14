package com.saitynai.project.saitynai.services;

import com.saitynai.project.saitynai.exceptions.BookNotFoundException;
import com.saitynai.project.saitynai.exceptions.MalformedRequestException;
import com.saitynai.project.saitynai.model.Book;
import com.saitynai.project.saitynai.repositories.BookRepository;
import com.saitynai.project.saitynai.requests.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(final BookRepository repository) {
        this.repository = repository;
    }

    public Book getBookById(String id) {
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new BookNotFoundException("Book is not found!");
    }

    public Book insertBook(BookRequest request) {
        validateRequest(request);
        Book book = new Book(
            request.getAuthor(),
            request.getTitle(),
            request.getAbout(),
            request.getYear(),
            LocalDateTime
                .now(),
            request.getIsbn()
        );
        return repository.save(book);
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book modifyBook(final String id, final BookRequest request) {
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()) {
            Book b = book.get();
            validateRequest(request);
            b.setAbout(request.getAuthor());
            b.setTitle(request.getTitle());
            b.setAbout(request.getAbout());
            b.setYear(request.getYear());
            b.setIsbn(request.getIsbn());
            return repository.save(b);
        }
        throw new BookNotFoundException("Tokios knygos nėra!");
    }

    public void deleteBookById(final String id) {
        if (!repository.existsById(id)) throw new BookNotFoundException("Book is not found!");
        repository.deleteById(id);
    }

    private void validateRequest(BookRequest request) {
        if (request.getAbout() == null || request.getAbout().length() == 0
            || request.getAuthor() == null || request.getAuthor().length() == 0
            || request.getIsbn() == null || request.getIsbn().length() == 0
            || request.getTitle() == null || request.getTitle().length() == 0
            || request.getYear() == null || request.getYear().length() == 0
        ) {
            throw new MalformedRequestException("Privalote užpildyti visus laukus!");
        }
    }
}
