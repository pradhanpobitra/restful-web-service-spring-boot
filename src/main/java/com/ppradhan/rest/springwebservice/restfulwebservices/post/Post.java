package com.ppradhan.rest.springwebservice.restfulwebservices.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ppradhan.rest.springwebservice.restfulwebservices.user.User;
import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post() {}

    public Post(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
