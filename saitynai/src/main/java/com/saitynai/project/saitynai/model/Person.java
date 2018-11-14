package com.saitynai.project.saitynai.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
public class Person {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
            "Person[id=%s, firstName='%s', lastName='%s']",
            id, firstName, lastName);
    }

}
