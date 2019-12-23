package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tbs.model.User;
import pl.tbs.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository users;

    @GetMapping("/users")
    public List<User> findUserByUsername(@RequestParam(value = "username", required = false) String username) {
        if(username == null) {
            return users.findAll();
        }
        else {
            List<User> foundUser = new ArrayList<>();
            foundUser.add(users.findByUsername(username));
            return foundUser;
        }
    }

    @GetMapping("/users/{userId}")
    public User findUserById(@RequestParam("userId") int id) {
        return  users.findById(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        user.setReservations(new ArrayList<>());
        user.setCreated(new Date());
        users.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteClient(@RequestParam(value = "userId") int id) {
        users.delete(users.findById(id));
    }

    @PutMapping("/users/{usersId}")
    public void updateUser(@RequestParam(value = "userId") int id, @RequestBody User user) {
        user.setId(id);
        users.save(user);
    }

}
