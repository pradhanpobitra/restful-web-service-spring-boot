package com.ppradhan.rest.springwebservice.restfulwebservices.user;

import com.ppradhan.rest.springwebservice.restfulwebservices.post.Post;
import com.ppradhan.rest.springwebservice.restfulwebservices.post.PostJpaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {
    private UserDaoService userDaoService;

    @Autowired
    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable Long id) {
        User user = userDaoService.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id: " + id);
        }

        // Spring HATEOAS implementation - HyperMedia As The Engine Of Application State
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User user1 = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + user1.getId().toString()).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDaoService.deleteById(id);
    }
}
