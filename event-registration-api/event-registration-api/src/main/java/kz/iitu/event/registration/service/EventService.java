
package kz.iitu.event.registration.service;

// Business logic and event management
import kz.iitu.event.registration.model.EventInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final List<EventInfo> events = new ArrayList<>();

    private int nextId = 4;

    public EventService() {
        events.add(new EventInfo(1, "Web Design Workshop", "Almaty"));
        events.add(new EventInfo(2, "JavaScript Quiz", "Room 101"));
        events.add(new EventInfo(3, "IT Career Talk", "Conference Hall"));
    }

    public String getWelcomeMessage() {
        return "Welcome to the Event Registration API!";
    }

    // GET: Get all events or search by title
    public List<EventInfo> getAllEvents(String title) {

        if (title == null || title.isBlank()) {
            return events;
        }

        return events.stream()
                .filter(event -> event.getTitle()
                        .toLowerCase()
                        .contains(title.toLowerCase()))
                .toList();
    }

    // GET: Find event by ID
    public Optional<EventInfo> getEventById(int id) {

        return events.stream()
                .filter(event -> event.getId() == id)
                .findFirst();
    }

    // POST: Create a new event
    public EventInfo createEvent(EventInfo event) {

        EventInfo newEvent = new EventInfo(
                nextId++,
                event.getTitle().trim(),
                event.getLocation().trim()
        );

        events.add(newEvent);

        return newEvent;
    }

    // PUT: Update an existing event
    public Optional<EventInfo> updateEvent(int id, EventInfo updatedEvent) {

        Optional<EventInfo> existingEvent = getEventById(id);

        if (existingEvent.isEmpty()) {
            return Optional.empty();
        }

        EventInfo event = existingEvent.get();

        event.setTitle(updatedEvent.getTitle().trim());
        event.setLocation(updatedEvent.getLocation().trim());

        return Optional.of(event);
    }

    // DELETE: Remove an event
    public boolean deleteEvent(int id) {

        return events.removeIf(event -> event.getId() == id);
    }
}
