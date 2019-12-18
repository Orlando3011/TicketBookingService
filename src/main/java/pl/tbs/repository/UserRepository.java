package pl.tbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tbs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
}
