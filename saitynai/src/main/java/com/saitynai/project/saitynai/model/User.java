package com.saitynai.project.saitynai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    @NonNull
    private String email;

    @NonNull
    @JsonIgnore
    private String password;

    private String token;

    @NonNull
    private String name;

    @NonNull
    private String surname;
}
