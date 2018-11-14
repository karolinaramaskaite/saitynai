package com.saitynai.project.saitynai.repositories;

import com.saitynai.project.saitynai.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {

    Person findByFirstName(String firstName);

    List<Person> findAllByFirstName(String firstName);

    List<Person> findByLastName(String lastName);
}
