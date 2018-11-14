package com.saitynai.project.saitynai.services;

import com.saitynai.project.saitynai.exceptions.BookNotFoundException;
import com.saitynai.project.saitynai.exceptions.MalformedRequestException;
import com.saitynai.project.saitynai.exceptions.ReviewNotFoundException;
import com.saitynai.project.saitynai.model.Book;
import com.saitynai.project.saitynai.model.Review;
import com.saitynai.project.saitynai.repositories.BookRepository;
import com.saitynai.project.saitynai.repositories.ReviewRepository;
import com.saitynai.project.saitynai.requests.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    private final BookRepository bookRepository;

    @Autowired
    public ReviewService(
        final ReviewRepository repository,
        final BookRepository bookRepository
    ) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    public void deleteReviewById(String id) {
        if (!repository.existsById(id)) throw new ReviewNotFoundException("Review is not found!");
        repository.deleteById(id);
    }

    public Book addReview(final String id, final ReviewRequest request) {
        validateRequest(request);
        Review review = repository.save(new Review(
            request.getUserEmail(),
            LocalDateTime.now(),
            request.getText()
        ));
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            List<Review> reviews = book.getReviews();
            if (reviews == null) {
                reviews = new ArrayList<>();
            }
            reviews.add(review);
            book.setReviews(reviews);
            return bookRepository.save(book);
        }
        throw new BookNotFoundException("Book is not found!");
    }

    private void validateRequest(ReviewRequest request) {
        if (request.getText() == null || request.getText().length() == 0
            || request.getUserEmail() == null || request.getUserEmail().length() == 0
        ) {
            throw new MalformedRequestException("Privalote užpildyti visus laukus!");
        }
    }
}
