package com.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class User {

    @Id
    private Integer id;

    private String name;
    private String userPick;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPick() {
        return userPick;
    }

    public void setUserPick(String userPick) {
        this.userPick = userPick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
