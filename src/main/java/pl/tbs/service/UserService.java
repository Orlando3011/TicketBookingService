package pl.tbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tbs.model.User;
import pl.tbs.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository users;

    public User getUserRole(User user) {
        User foundUser = users.findByUsername(user.getUsername());
        if(foundUser != null) {
            if(foundUser.getPassword().equals(user.getPassword())) {
                return this.returnUserCredentials(user);
            }
            else return null;
        }
        else return null;
    }

    public User returnUserCredentials(User user) {
        user.setCreated(null);
        user.setReservations(null);
        user.setAddress(null);
        user.setFamilyName(null);
        user.setFirstName(null);
        user.setPhoneNumber(null);
        return user;
    }
}
