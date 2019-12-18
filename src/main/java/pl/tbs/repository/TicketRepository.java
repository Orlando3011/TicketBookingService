package pl.tbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tbs.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findById(int id);
}
