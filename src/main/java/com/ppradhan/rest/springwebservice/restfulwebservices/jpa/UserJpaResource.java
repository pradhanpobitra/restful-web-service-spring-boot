package com.ppradhan.rest.springwebservice.restfulwebservices.jpa;

import com.ppradhan.rest.springwebservice.restfulwebservices.post.Post;
import com.ppradhan.rest.springwebservice.restfulwebservices.post.PostJpaRepository;
import com.ppradhan.rest.springwebservice.restfulwebservices.post.PostNotFoundException;
import com.ppradhan.rest.springwebservice.restfulwebservices.user.User;
import com.ppradhan.rest.springwebservice.restfulwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jpa/users")
public class UserJpaResource {
    private UserRepository userRepository;
    private PostJpaRepository postJpaRepository;

    @Autowired
    public UserJpaResource(UserRepository userRepository, PostJpaRepository postJpaRepository) {
        this.userRepository = userRepository;
        this.postJpaRepository = postJpaRepository;
    }

    @GetMapping("")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        // Spring HATEOAS implementation - HyperMedia As The Engine Of Application State
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User user1 = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + user1.getId().toString()).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Long id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        post.setUser(user.get());
        Post savedPost = postJpaRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + savedPost.getId().toString()).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/posts/{postId}")
    public Post getPostById(@PathVariable("id") Long id, @PathVariable("postId") Long postId) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        Optional<Post> post = postJpaRepository.findById(postId);
        if(post.isEmpty()) {
            throw new PostNotFoundException("Post with id:" + postId + " does not exist");
        }
        return post.get();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
