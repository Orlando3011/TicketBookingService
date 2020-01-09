package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tbs.model.User;
import pl.tbs.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository users;

    @GetMapping("/users")
    public List<User> findUserByUsername() {
            return users.findAll();
    }

    @GetMapping("/users/{userId}")
    public User findUserById(@PathVariable("userId") int id) {
        return  users.findById(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        user.setReservations(new ArrayList<>());
        users.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteClient(@PathVariable(value = "userId") int id) {
        users.delete(users.findById(id));
    }

    @PutMapping("/users/{usersId}")
    public void updateUser(@PathVariable(value = "userId") int id, @RequestBody User user) {
        user.setId(id);
        users.save(user);
    }
}
