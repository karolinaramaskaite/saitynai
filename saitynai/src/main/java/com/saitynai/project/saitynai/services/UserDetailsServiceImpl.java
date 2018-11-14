package com.saitynai.project.saitynai.services;

import com.saitynai.project.saitynai.model.MyUserDetails;
import com.saitynai.project.saitynai.model.User;
import com.saitynai.project.saitynai.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return new MyUserDetails(user);
        }
        throw new UsernameNotFoundException(username);
    }
}
