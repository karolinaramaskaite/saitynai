package com.saitynai.project.saitynai.repositories;

import com.saitynai.project.saitynai.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(String id);

}
