package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tbs.exception.NoTicketsLeftException;
import pl.tbs.model.Ticket;
import pl.tbs.repository.ReservationRepository;
import pl.tbs.repository.TicketRepository;
import pl.tbs.service.TicketService;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketRepository tickets;
    @Autowired
    private ReservationRepository reservations;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/users/{userId}/reservations/{reservationId}/tickets")
    public List<Ticket> showTicketsInReservation(@PathVariable("reservationId") int id) {
        return reservations.findById(id).getTickets();
    }

    @PostMapping("/users/{userId}/reservations/{reservationId}/events/{eventId}/tickets")
    public void addTicket(@PathVariable("reservationId") int reservationId, @PathVariable("eventId") int eventId, @RequestBody Ticket ticket) throws NoTicketsLeftException {
        ticket = ticketService.prepareNewTicket(ticket, eventId, reservationId);
        tickets.save(ticket);
    }

    @DeleteMapping("/users/{userId}/reservations/{reservationId}/events/{eventId}/tickets/{ticketId}")
    public void removeTicket(@PathVariable("reservationId") int reservationId, @PathVariable("ticketId") int ticketId, @PathVariable("eventId") int eventId) {
        Ticket ticket = tickets.findById(ticketId);
        ticketService.removeTicketInfo(ticket, reservationId, eventId);
        tickets.delete(ticket);
    }
}
