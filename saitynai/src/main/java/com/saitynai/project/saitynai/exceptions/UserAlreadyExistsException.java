package com.saitynai.project.saitynai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
