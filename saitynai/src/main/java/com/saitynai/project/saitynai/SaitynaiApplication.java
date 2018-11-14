package com.saitynai.project.saitynai;

import com.saitynai.project.saitynai.repositories.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories//(basePackageClasses = PersonRepository.class)
public class SaitynaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaitynaiApplication.class, args);
    }
}
