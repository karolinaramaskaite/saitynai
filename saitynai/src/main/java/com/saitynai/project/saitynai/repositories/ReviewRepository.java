package com.saitynai.project.saitynai.repositories;

import com.saitynai.project.saitynai.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {

}
