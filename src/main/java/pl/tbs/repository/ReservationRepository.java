package pl.tbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tbs.model.Reservation;
import pl.tbs.model.User;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findById(int id);
    List<Reservation> findByCreated(Date created);
    List<Reservation> findByUser(User user);
}
