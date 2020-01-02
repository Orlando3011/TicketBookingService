package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.tbs.model.Reservation;
import pl.tbs.model.User;
import pl.tbs.repository.ReservationRepository;
import pl.tbs.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void addReservation(@PathVariable("userId") int id, @RequestBody Reservation reservation) {
        reservation.setTickets(new ArrayList<>());
        reservation.setDateCreated(new Date());
        reservations.save(reservation);

         User user = users.findById(id);
         user.addReservation(reservation);
         users.save(user);
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
