package com.saitynai.project.saitynai.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "book")
public class Book {

    @Id
    private String id;

    @NonNull
    private String author;

    @NonNull
    private String title;

    @NonNull
    private String about;

    @NonNull
    private String year;

    @NonNull
    private LocalDateTime createdOn;

    @NonNull
    private String isbn;

    private List<Review> reviews;
}
