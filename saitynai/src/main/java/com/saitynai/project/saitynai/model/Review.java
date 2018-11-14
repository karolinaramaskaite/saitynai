package com.saitynai.project.saitynai.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "review")
public class Review {

    @Id
    private String id;

    @NonNull
    private String userEmail;

    @NonNull
    private LocalDateTime createdAt;

    @NonNull
    private String text;
}
