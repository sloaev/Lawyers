package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class LoginController {

    @GetMapping("/login")
    private String login(){
        return "login";
    }

    @GetMapping("/logout")
    private String logout(){
        return "logout";
    }

    @GetMapping("/def")
    private String def(){
        return "def";
    }

}
