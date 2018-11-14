package com.saitynai.project.saitynai.repositories;

import com.saitynai.project.saitynai.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String username);

    Long countByEmail(String email);

    @Transactional
    Long deleteByNameIsNull();

//    User findByUsername(String username);
}
