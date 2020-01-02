package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tbs.model.Event;
import pl.tbs.repository.EventRepository;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventRepository events;

    @GetMapping("/events")
    public List<Event> findAllEvents() {
        return events.findAll();
    }

    @GetMapping("/events/{eventId}")
    public Event findEventById(@PathVariable("eventId") int id) {
        return events.findById(id);
    }

    @GetMapping("/events/{eventType}")
    public List<Event> findEventsByType(@RequestParam("eventType") String type) {
        return events.findByEventType(type);
    }

    @PostMapping("/events")
    public void addEvent(@RequestBody Event event) {
        event.setTicketsAvailable(event.getTicketsTotal());
        events.save(event);
    }

    @PutMapping("/events/{eventId}")
    public void editEvent(@PathVariable("eventId") int id, @RequestBody Event event) {
        event.setId(id);
        events.save(event);
    }

    @DeleteMapping("events/{eventId}")
    public void deleteEvent(@PathVariable("eventId") int id) {
        events.delete(events.findById(id));
    }
}
