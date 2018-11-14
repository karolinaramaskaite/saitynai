package com.saitynai.project.saitynai.controllers;

import com.saitynai.project.saitynai.model.User;
import com.saitynai.project.saitynai.repositories.UserRepository;
import com.saitynai.project.saitynai.requests.UserRequest;
import com.saitynai.project.saitynai.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    private final UserRepository repository;

    private final UserService userService;

    public UserController(
        final UserRepository repository,
        final UserService userService
    ) {
        this.repository = repository;
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody UserRequest request) {
        return userService.registerUser(request);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
