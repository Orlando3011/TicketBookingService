package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.tbs.model.Event;
import pl.tbs.model.Reservation;
import pl.tbs.model.Ticket;
import pl.tbs.repository.EventRepository;
import pl.tbs.repository.ReservationRepository;
import pl.tbs.repository.TicketRepository;

import java.util.List;

@Controller
public class TicketController {
    @Autowired
    private TicketRepository tickets;
    @Autowired
    private ReservationRepository reservations;
    @Autowired
    private EventRepository events;

    @GetMapping("/users/{userId}/reservations/{reservationId}/tickets")
    public List<Ticket> showTicketsInReservation(@RequestParam("reservationId") int id) {
        return reservations.findById(id).getTickets();
    }

    @PostMapping("/users/{userId}/reservations/{reservationId}/events/{eventId}/tickets")
    public void addTicket(@RequestParam("reservationId") int reservationId, @RequestParam("eventId") int eventId, @RequestBody Ticket ticket) {

        Event event = events.findById(eventId);
        ticket.setEvent(event);
        event.setTicketsAvailable(event.getTicketsAvailable() - 1);

        Reservation reservation = reservations.findById(reservationId);
        reservation.addTicket(ticket);
        tickets.save(ticket);
        reservations.save(reservation);
        events.save(event);
    }

    @DeleteMapping("/users/{userId}/reservations/{reservationId}/events/{eventId}/tickets/{ticketId}")
    public void removeTicket(@RequestParam("reservationId") int reservationId, @RequestParam("ticketId") int ticketId, @RequestParam("eventId") int eventId) {
        Event event = events.findById(eventId);
        event.setTicketsAvailable(event.getTicketsAvailable() + 1);
        Reservation reservation = reservations.findById(reservationId);
        Ticket ticket = tickets.findById(ticketId);
        reservation.removeTicket(ticket);
        tickets.delete(ticket);
        reservations.save(reservation);
        events.save(event);
    }
}
