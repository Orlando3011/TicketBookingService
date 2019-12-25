package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.tbs.model.Reservation;
import pl.tbs.model.Ticket;
import pl.tbs.repository.ReservationRepository;
import pl.tbs.repository.TicketRepository;

import java.util.List;

@Controller
public class TicketController {
    @Autowired
    private TicketRepository tickets;
    @Autowired
    private ReservationRepository reservations;

    @GetMapping("/users/{userId}/reservations/{reservationId}/tickets")
    public List<Ticket> showTicketsInReservation(@RequestParam("reservationId") int id) {
        return reservations.findById(id).getTickets();
    }

    @PostMapping("/users/{userId}/reservations/{reservationId}/tickets")
    public void addTicket(@RequestParam("reservationId") int id, @RequestBody Ticket ticket) {
        Reservation reservation = reservations.findById(id);
        reservation.addTicket(ticket);
        tickets.save(ticket);
        reservations.save(reservation);
    }

    @DeleteMapping("/users/{userId}/reservations/{reservationId}/tickets/{ticketId}")
    public void removeTicket(@RequestParam("reservationId") int reservationId, @RequestParam("ticketId") int ticketId) {
        Reservation reservation = reservations.findById(reservationId);
        Ticket ticket = tickets.findById(ticketId);
        reservation.removeTicket(ticket);
        tickets.delete(ticket);
        reservations.save(reservation);
    }
}
