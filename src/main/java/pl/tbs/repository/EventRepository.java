package pl.tbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tbs.model.Event;
import pl.tbs.model.EventType;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findById(int id);
    Event findByEventType(EventType eventTypeype);
}
