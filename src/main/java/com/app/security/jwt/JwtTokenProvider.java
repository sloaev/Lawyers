package com.app.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private Long validityInSeconds;

    @Autowired
    UserDetailsService userDetailsService;

    public String createToken(String userName) {

    }

    public Authentication authentication(String token) {

    }

    public String getUserName (String token) {

    }

    public boolean validateToken (String token) {

    }


}
