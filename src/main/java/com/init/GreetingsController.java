package com.init;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class GreetingsController {

    @GetMapping("/privet")
    public String greeting() {
        return "privet";
    }
}
