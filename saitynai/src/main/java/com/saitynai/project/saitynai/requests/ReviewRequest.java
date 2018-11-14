package com.saitynai.project.saitynai.requests;

import lombok.Data;

@Data
public class ReviewRequest {

    private String userEmail;

    private String text;
}
