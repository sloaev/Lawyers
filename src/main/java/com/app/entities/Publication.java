package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Model for working with publication db entity
 */
@Entity(name = "publications")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String headline;

    private String content;

    private Date date;

    public Integer getId() {
        return id;
    }

    public Publication() {
    }

    /**
     * @param headline  headline of publication
     * @param content publication content
     */
    public Publication(String headline, String content) {
        this.headline = headline;
        this.content = content;
    }

    /**
     *
     * @param headline  headline of publication
     * @param content publication content
     * @param date  date of creation
     */
    public Publication(String headline, String content, Date date) {
        this.headline = headline;
        this.content = content;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
