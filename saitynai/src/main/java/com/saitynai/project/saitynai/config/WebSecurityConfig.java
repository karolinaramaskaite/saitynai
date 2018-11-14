package com.saitynai.project.saitynai.config;

import com.saitynai.project.saitynai.repositories.UserRepository;
import com.saitynai.project.saitynai.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userMapper;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers("/api/auth").permitAll()
            .antMatchers("/users").permitAll()
            .antMatchers("/user/login").permitAll()
            .antMatchers("/api/all-books").permitAll()
            .antMatchers("/swagger-ui.html**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager(), userMapper))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcryptEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/")
            .antMatchers("/swagger-ui.html**")
            .antMatchers("/favicon.ico")
            .antMatchers("/v2/api-docs")
            .antMatchers("/swagger-resources/**")
            .antMatchers("/webjars/springfox-swagger-ui/**");

    }

    @Bean
    public PasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }
}
