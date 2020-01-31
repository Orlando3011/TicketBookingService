package pl.tbs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tbs.exception.NoTicketsLeftException;
import pl.tbs.model.Event;
import pl.tbs.model.Reservation;
import pl.tbs.model.Ticket;
import pl.tbs.repository.EventRepository;
import pl.tbs.repository.ReservationRepository;
import pl.tbs.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    Ticket ticket;
    Event event;
    int eventId = 1;
    int reservationId = 1;
    @Autowired
    TicketService ticketService;
    @MockBean
    private TicketRepository tickets;
    @MockBean
    private ReservationRepository reservations;
    @MockBean
    private EventRepository events;

    //given
    @Before
    public void createNeededObjects() {
        ticket = new Ticket();
        event = new Event();
    }

    @Test
    public void shouldThrowNoTicketsLeftException() throws NoTicketsLeftException {
        //when
        when(events.findById(anyInt())).thenReturn(new Event(0));
        //then
        assertThrows(NoTicketsLeftException.class, () -> ticketService.prepareNewTicket(ticket, eventId, reservationId));
    }

    @Test
    public void shouldReturnTicketWithReservation() throws NoTicketsLeftException {
        //when
        ticket.setDiscounted(false);
        when(events.findById(anyInt())).thenReturn(new Event(10, 20));
        when(reservations.findById(anyInt())).thenReturn(new Reservation());
        //then
        assertNotNull(ticketService.prepareNewTicket(ticket, eventId, reservationId).getReservation());
    }

    @Test
    public void shouldReturnTicketWithEvent() throws NoTicketsLeftException {
        //when
        ticket.setDiscounted(false);
        when(events.findById(anyInt())).thenReturn(new Event(10, 20));
        when(reservations.findById(anyInt())).thenReturn(new Reservation());
        //then
        assertNotNull(ticketService.prepareNewTicket(ticket, eventId, reservationId).getEvent());
    }

}