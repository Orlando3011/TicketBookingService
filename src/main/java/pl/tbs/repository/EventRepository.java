package pl.tbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tbs.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findById(int id);
    List<Event> findByEventType(String eventType);
    List<Event> findByPlace(String place);
}
