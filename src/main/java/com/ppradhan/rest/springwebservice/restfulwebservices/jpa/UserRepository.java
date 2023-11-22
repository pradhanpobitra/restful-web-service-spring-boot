package com.ppradhan.rest.springwebservice.restfulwebservices.jpa;

import com.ppradhan.rest.springwebservice.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
