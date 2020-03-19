package com.app.controllers;

import com.app.entities.Publication;
import com.app.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class PublicationsController {
    @Autowired
    PublicationService publicationService;

    @GetMapping("/privet")
    public String greeting() {
        return "privet";
    }

    @GetMapping("/getPubs")
    public List<Publication> getPubs() {
        return publicationService.findAllBy();
    }

    @PostMapping("/addPub")
    public void addPub(@RequestParam String headLine, @RequestParam String content) {
        Publication publication = new Publication(headLine, content);
        publicationService.save(publication);
    }

    @PostMapping("/editPub")
    public void editPub(@RequestParam String headLine, @RequestParam String content, @RequestParam Integer id) {
        Publication publication = publicationService.getById(id);
        publication.setContent(content);
        publication.setHeadline(headLine);
        publicationService.save(publication);
    }

    @PostMapping("/deletePub")
    public void deletePub(@RequestParam Integer id) {
        Publication publication = publicationService.getById(id);
        publicationService.delete(publication);
    }
}
