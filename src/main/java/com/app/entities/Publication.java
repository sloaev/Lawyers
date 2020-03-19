package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "publications")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String headline;

    private String content;

    public Integer getId() {
        return id;
    }

    public Publication() {
    }

    public Publication(String headline, String content) {
        this.headline = headline;
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
