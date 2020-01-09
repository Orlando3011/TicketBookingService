package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.tbs.model.Event;
import pl.tbs.model.Reservation;
import pl.tbs.model.Ticket;
import pl.tbs.model.User;
import pl.tbs.repository.EventRepository;
import pl.tbs.repository.TicketRepository;
import pl.tbs.repository.UserRepository;

import java.util.*;

@RestController
public class RecommendationController {
    @Autowired
    TicketRepository tickets;
    @Autowired
    UserRepository users;
    @Autowired
    EventRepository events;

    @GetMapping("/users/{userId}/genreRecommendations")
    public List<Event> listEventsMatchingTypes(@PathVariable("userId") int userId) {
        List<Event> eventsVisited = getEventsVisitedByUser(users.findById(userId));
        List<Event> eventsRecommended = events.findByEventType(getBestGenre(eventsVisited));
        eventsRecommended.removeIf(eventsVisited::contains);
        return  eventsRecommended;
    }

    @GetMapping("/users/{userId}/placeRecommendations")
    public List<Event> listEventMatchingPlaces(@PathVariable("userId") int userId) {
        List<Event> eventsVisited = getEventsVisitedByUser(users.findById(userId));
        List<Event> eventsRecommended = events.findByPlace(getBestPlace(eventsVisited));
        eventsRecommended.removeIf(eventsVisited::contains);
        return  eventsRecommended;
    }

    private List<Event> getEventsVisitedByUser(User user) {
        List<Event> eventsVisited = new ArrayList<>();
        List<Ticket> ticketsBooked = new ArrayList<>();
        List<Reservation> reservationsMade = user.getReservations();

        for (Reservation reservation: reservationsMade) {
            ticketsBooked.addAll(reservation.getTickets());
        }

        for (Ticket ticket: ticketsBooked) {
            eventsVisited.add(ticket.getEvent());
        }
        HashSet<Event> uniqueEvents =  new HashSet<>(eventsVisited);
        return new ArrayList<>(uniqueEvents);
    }

    private String getBestGenre(List<Event> events) {
        List<String> genres = new ArrayList<>();
        Map<String, Integer> genresCounted = new HashMap<>();

        for (Event event: events) {
            genres.add(event.getEventType());
        }

        for (String genre: genres) {
            Integer val = genresCounted.get(genre);
            genresCounted.put(genre, val == null ? 1 : val + 1);
        }

        Map.Entry<String, Integer> max = null;

        for (Map.Entry<String, Integer> e : genresCounted.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        assert max != null;
        return max.getKey();
    }

    private String getBestPlace(List<Event> events) {
        List<String> places = new ArrayList<>();
        Map<String, Integer> placesCounted = new HashMap<>();

        for (Event event: events) {
            places.add(event.getPlace());
        }

        for (String place: places) {
            Integer val = placesCounted.get(place);
            placesCounted.put(place, val == null ? 1 : val + 1);
        }

        Map.Entry<String, Integer> max = null;

        for (Map.Entry<String, Integer> e : placesCounted.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        assert max != null;
        return max.getKey();
    }

}
