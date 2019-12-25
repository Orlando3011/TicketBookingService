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

@Controller
public class ReservationController {
    @Autowired
    UserRepository users;
    @Autowired
    ReservationRepository reservations;

    @GetMapping("/users/{userId}/reservations")
    public List<Reservation> showUserReservations(@RequestParam("userId") int id) {
        return  users.findById(id).getReservations();
    }

    @PostMapping("/users/{userId}/reservations")
    public void addReservation(@RequestParam("userId") int id, @RequestBody Reservation reservation) {
        reservation.setTickets(new ArrayList<>());
        reservation.setDateCreated(new Date());
        reservations.save(reservation);

         User user = users.findById(id);
         user.addReservation(reservation);
         users.save(user);
    }

    @DeleteMapping("/users/{userId}/reservations/{reservationId}")
    public void removeReservation(@RequestParam("userId") int userId, @RequestParam("reservationId") int reservationId) {
        User user = users.findById(userId);
        Reservation reservation = reservations.findById(reservationId);
        user.deleteReservation(reservation);
        users.save(user);
        reservations.delete(reservation);
    }
}
