package pl.tbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.tbs.model.Event;
import pl.tbs.service.RecommendationService;

import java.util.*;

@CrossOrigin
@RestController
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/users/{userId}/genreRecommendations")
    public List<Event> listEventsMatchingTypes(@PathVariable("userId") int userId) {
        return recommendationService.recommendByType(userId);
    }

    @GetMapping("/users/{userId}/placeRecommendations")
    public List<Event> listEventMatchingPlaces(@PathVariable("userId") int userId) {
        return  recommendationService.recommendByPlace(userId);
    }
}
