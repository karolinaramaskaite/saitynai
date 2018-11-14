package com.saitynai.project.saitynai.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class SwaggerController {

    @GetMapping("/")
    public RedirectView redirectToSwagger() {
        return new RedirectView("/swagger-ui.html", true);
    }
}
