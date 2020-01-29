package pl.tbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tbs.model.User;
import pl.tbs.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository users;

    public String getUserRole(User user) {
        User foundUser = users.findByUsername(user.getUsername());
        if(foundUser != null) {
            if(foundUser.getPassword().equals(user.getPassword())) {
                return foundUser.getRole();
            }
            else return null;
        }
        else return null;
    }
}
