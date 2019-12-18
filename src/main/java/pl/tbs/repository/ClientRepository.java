package pl.tbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tbs.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findById(int id);
    Client findByUsername(String username);
}
