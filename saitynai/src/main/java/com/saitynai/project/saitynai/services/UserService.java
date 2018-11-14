package com.saitynai.project.saitynai.services;

import static com.saitynai.project.saitynai.config.AuthenticationConstants.EXPIRATION_TIME;
import static com.saitynai.project.saitynai.config.AuthenticationConstants.KEY;

import com.saitynai.project.saitynai.exceptions.MalformedRequestException;
import com.saitynai.project.saitynai.exceptions.UserAlreadyExistsException;
import com.saitynai.project.saitynai.exceptions.UserNotFoundException;
import com.saitynai.project.saitynai.model.User;
import com.saitynai.project.saitynai.repositories.UserRepository;
import com.saitynai.project.saitynai.requests.UserRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserService(
        final UserRepository userRepository,
        final PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User login(String username, String password) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UserNotFoundException("Vartotojo vardas arba slaptažodis yra neteisingas");
        }
        if (!encoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException("Vartotojo vardas arba slaptažodis yra neteisingas");
        }
        String token = getAuthorizationToken(username);
        user.setToken(token);
        return userRepository.save(user);
    }

    private String getAuthorizationToken(String username) {
        return generateToken(username);
    }

    private String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, KEY)
            .compact();
    }

    public void  logout(User user) {
        user.setToken("");
        userRepository.save(user);
    }

    public User registerUser(UserRequest request) {
        Long users = userRepository.countByEmail(request.getEmail());
        if (users == 0) {
            validateRequest(request);
            User user = new User(
                request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getName(),
                request.getSurname()
            );
            return userRepository.save(user);
        }
        throw new UserAlreadyExistsException("Toks vartotojas jau egzistuoja!");
    }

    private void validateRequest(UserRequest request) {
        if (request.getEmail() == null || request.getEmail().length() == 0
            || request.getName() == null || request.getName().length() == 0
            || request.getSurname() == null || request.getSurname().length() == 0
            || request.getPassword() == null || request.getPassword().length() == 0
        ) {
            throw new MalformedRequestException("Privalote užpildyti visus laukus!");
        }
    }
}
