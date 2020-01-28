package pl.tbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tbs.exception.NoTicketsLeftException;
import pl.tbs.model.Event;
import pl.tbs.model.Reservation;
import pl.tbs.model.Ticket;
import pl.tbs.repository.EventRepository;
import pl.tbs.repository.ReservationRepository;
import pl.tbs.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository tickets;
    @Autowired
    private ReservationRepository reservations;
    @Autowired
    private EventRepository events;


    public Ticket prepareNewTicket(Ticket ticket, int eventId, int reservationId) throws NoTicketsLeftException {
        Event event  = events.findById(eventId);

        if(event.getTicketsAvailable() == 0) {
            throw new NoTicketsLeftException();
        }

        this.setTicketPrice(ticket);
        ticket.setEvent(event);
        event.setTicketsAvailable(event.getTicketsAvailable() - 1);

        Reservation reservation = reservations.findById(reservationId);
        ticket.setReservation(reservation);

        reservations.save(reservation);
        events.save(event);
        return ticket;
    }

    public void removeTicketInfo(Ticket ticket, int reservationId, int eventId) {
        Event event = events.findById(eventId);
        event.setTicketsAvailable(event.getTicketsAvailable() + 1);
        events.save(event);

        Reservation reservation = reservations.findById(reservationId);
        reservation.removeTicket(ticket);
        reservations.save(reservation);
    }

    private void setTicketPrice(Ticket ticket) {
        Event event  = ticket.getEvent();
        if(ticket.isDiscounted()) {
            ticket.setPrice(event.getDiscountTicketPrice());
        }
        else {
            ticket.setPrice(event.getNormalTicketPrice());
        }
    }
}
