package com.user.model;

import java.io.Serializable;

// save com.user registration
public class User implements Serializable {

    // com.user variables
    private Integer id = 0;
    private String name = "";
    private String email = "";
    private String twitter = "";
    private String description = "";

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.twitter = "";
        this.description = "";
    }

    public User(String name, String email, String twitter, String description) {
        this.name = name;
        this.email = email;
        this.twitter = twitter;
        this.description = description;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
