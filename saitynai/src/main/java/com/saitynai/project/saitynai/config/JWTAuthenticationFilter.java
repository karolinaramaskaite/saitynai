package com.saitynai.project.saitynai.config;

import static com.saitynai.project.saitynai.config.AuthenticationConstants.HEADER;
import static com.saitynai.project.saitynai.config.AuthenticationConstants.KEY;
import static com.saitynai.project.saitynai.config.AuthenticationConstants.TOKEN_PREFIX;

import com.saitynai.project.saitynai.model.User;
import com.saitynai.project.saitynai.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private UserRepository userMapper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userMapper) {
        super(authenticationManager);
        this.userMapper = userMapper;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is invalid");
            return;
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        if (header != null) {
            String username = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(header.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();


            if (username != null) {
                User user = userMapper.findByEmail(username);
                if (user == null) {
                    throw new RuntimeException("User doesn't exist");
                }
                if (user.getToken() != null && user.getToken().equals(header.replace(TOKEN_PREFIX, ""))) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            }
            return null;
        }
        return null;
    }
}
