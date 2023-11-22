package com.ppradhan.rest.springwebservice.restfulwebservices.user;

import com.ppradhan.rest.springwebservice.restfulwebservices.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Name must not be blank")
//    @JsonProperty("user_name")
    private String name;

    @PastOrPresent(message = "Birth date must not be a future date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public User() {

    }

    public User(Long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
