package com.saitynai.project.saitynai.controllers;

import com.saitynai.project.saitynai.model.Book;
import com.saitynai.project.saitynai.requests.ReviewRequest;
import com.saitynai.project.saitynai.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(final ReviewService reviewService) {
        this.service = reviewService;
    }

    @PatchMapping(value = "books/{id}/reviews", headers = {"Authorization"})
    public Book addReview(@PathVariable String id, @RequestBody ReviewRequest request) {
        return service.addReview(id, request);
    }
}
