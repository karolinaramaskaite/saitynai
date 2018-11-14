package com.saitynai.project.saitynai.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MalformedRequestException extends RuntimeException {

    public MalformedRequestException(final String s) {
        super(s);
    }
}
