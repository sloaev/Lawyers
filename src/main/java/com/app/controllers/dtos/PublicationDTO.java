package com.app.controllers.dtos;

/**
 * DTO for transferring publications
 */
public class PublicationDTO {

    private Integer id;
    private String content;
    private String headline;
    private String image;
    private String date;


    public PublicationDTO() {
    }

    /**
     * @param id id publication
     * @param content publication content
     * @param headline publication headline
     * @param image publication image
     */
    public PublicationDTO(Integer id, String content, String headline, String image) {
        this.id = id;
        this.content = content;
        this.headline = headline;
        this.image = image;
    }

    /**
     * @param id id publication
     * @param content publication content
     * @param headline publication headline
     */
    public PublicationDTO(Integer id, String content, String headline) {
        this.id = id;
        this.content = content;
        this.headline = headline;
    }

    /**
     * @param content id publication
     * @param headline publication headline
     * @param image publication image
     */
    public PublicationDTO(String content, String headline, String image) {
        this.content = content;
        this.headline = headline;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
