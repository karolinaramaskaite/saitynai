package com.saitynai.project.saitynai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(final String message) {
        super(message);
    }
}
