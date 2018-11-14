package com.saitynai.project.saitynai.controllers;

import com.saitynai.project.saitynai.model.User;
import com.saitynai.project.saitynai.requests.LoginUserRequest;
import com.saitynai.project.saitynai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "user/login")
    public User login(@RequestBody  LoginUserRequest request) {
        return userService.login(request.getUsername(), request.getPassword());
    }

    @GetMapping(path = "user/logout", headers = {"Authorization"})
    public ResponseEntity logout(@AuthenticationPrincipal User user) {
        userService.logout(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
