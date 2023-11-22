package com.ppradhan.rest.springwebservice.restfulwebservices.user;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L, "Dhoni", LocalDate.now().minusYears(40L)));
        users.add(new User(2L, "Rohit", LocalDate.now().minusYears(35L)));
        users.add(new User(3L, "Virat", LocalDate.now().minusYears(39L)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Long id) {
        return users.stream().filter(
                user -> user.getId().equals(id)
        ).findFirst().orElse(null);
    }

    public void deleteById(Long id) {
        users.removeIf(
                user -> user.getId().equals(id)
        );
    }

    public User save(User user) {
        Long nextId = (long) (users.size() + 1);
        user.setId(nextId);
        users.add(user);
        return user;
    }
}
