package com.app.controllers;

import com.app.DTO.AuthenticationDTO;
import com.app.entities.User;
import com.app.security.jwt.JwtTokenProvider;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }


    public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getUserName(), authenticationDTO.getPassword()));
            User user = userService.getByName(authenticationDTO.getUserName());

            if (user == null) {
                throw new UsernameNotFoundException("User with username:" + authenticationDTO.getUserName() + "not found");
            }

            String token = jwtTokenProvider.createToken(user.getName());

            Map<String, String> response = new HashMap<>();
            response.put("username" , user.getName());
            response.put("token" , token);

            return ResponseEntity.ok(response);


        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
