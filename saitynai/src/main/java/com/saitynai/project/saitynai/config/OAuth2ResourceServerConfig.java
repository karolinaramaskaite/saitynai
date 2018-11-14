package com.saitynai.project.saitynai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Map;
//
//@Configuration
//@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer config) {
//        config.tokenServices(tokenServices());
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests().antMatchers("/swagger-ui.html**").permitAll()
//            .antMatchers("/login").permitAll()
//            .anyRequest().authenticated();
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("123");
//        return converter;
//    }
//
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        return defaultTokenServices;
//    }
//
//    public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
//        OAuth2AuthenticationDetails details
//            = (OAuth2AuthenticationDetails) auth.getDetails();
//        OAuth2AccessToken accessToken = tokenStore()
//            .readAccessToken(details.getTokenValue());
//        return accessToken.getAdditionalInformation();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
