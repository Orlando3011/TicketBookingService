package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tbs.model.Reservation;
import pl.tbs.model.User;
import pl.tbs.repository.ReservationRepository;
import pl.tbs.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ReservationController {
    @Autowired
    UserRepository users;
    @Autowired
    ReservationRepository reservations;

    @GetMapping("/reservations")
    public List<Reservation> listReservations() {
        return reservations.findAll();
    }

    @GetMapping("/reservations/users/{userId}")
    public List<Reservation> showUserReservations(@PathVariable("userId") int id) {
        return reservations.findByUser(users.findById(id));
    }

    @PostMapping("/reservations/users/{userId}")
    public Reservation addReservation(@PathVariable("userId") int id, @RequestBody Reservation reservation) {
        User user = users.findById(id);
        reservation.setUser(user);
        reservation.setTickets(new ArrayList<>());
        reservations.save(reservation);
        return reservation;
    }

    @DeleteMapping("/reservations/{reservationId}/users/{userId}")
    public void removeReservation(@PathVariable("userId") int userId, @PathVariable("reservationId") int reservationId) {
        User user = users.findById(userId);
        Reservation reservation = reservations.findById(reservationId);
        user.deleteReservation(reservation);
        users.save(user);
        reservations.delete(reservation);
    }
}
