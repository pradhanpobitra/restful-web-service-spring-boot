package com.ppradhan.rest.springwebservice.restfulwebservices.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
