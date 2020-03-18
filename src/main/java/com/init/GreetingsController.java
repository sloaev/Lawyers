package com.init;

import com.app.entities.Publication;
import com.app.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class GreetingsController {
    @Autowired
    PublicationService publicationService;

    @GetMapping("/privet")
    public String greeting() {
        return "privet";
    }

    @GetMapping("/db")
    public String getDb() {
        Iterable <Publication> publications = publicationService.findAll();
        List<Publication> pubs = new ArrayList<>();
        publications.forEach(pubs::add);
        return pubs.get(0).getHeadline();
    }
}
