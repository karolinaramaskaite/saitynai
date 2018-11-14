package com.saitynai.project.saitynai.requests;

import lombok.Data;

@Data
public class BookRequest {

    private String author;

    private String title;

    private String about;

    private String year;

    private String isbn;
}
