
package kz.iitu.event.registration.controller;

// Controller handles HTTP requests
import kz.iitu.event.registration.model.EventInfo;
import kz.iitu.event.registration.service.EventService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final EventService eventService;

    @Value("${app.course}")
    private String course;

    @Value("${app.semester}")
    private int semester;

    // Constructor Injection
    public RegistrationController(EventService eventService) {
        this.eventService = eventService;
    }

    // GET: Welcome message
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", eventService.getWelcomeMessage());
    }

    // GET: Application information
    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        return Map.of(
                "application", "event-registration-api",
                "course", course,
                "semester", semester
        );
    }

    // GET: Get all events or search by title
    @GetMapping("/events")
    public ResponseEntity<?> getEvents(
            @RequestParam(required = false) String title) {

        return ResponseEntity.ok(
                eventService.getAllEvents(title)
        );
    }

    // GET: Get event by ID
    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {

        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create a new event
    @PostMapping("/events")
    public ResponseEntity<?> createEvent(
            @RequestBody(required = false) EventInfo event) {

        if (!isValid(event)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message",
                            "Title and location must not be blank"));
        }

        EventInfo createdEvent = eventService.createEvent(event);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdEvent);
    }

    // PUT: Update an existing event
    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(
            @PathVariable int id,
            @RequestBody(required = false) EventInfo event) {

        if (!isValid(event)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message",
                            "Title and location must not be blank"));
        }

        return eventService.updateEvent(id, event)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Delete an event
    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable int id) {

        boolean deleted = eventService.deleteEvent(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Basic input validation
    private boolean isValid(EventInfo event) {

        return event != null
                && event.getTitle() != null
                && !event.getTitle().isBlank()
                && event.getLocation() != null
                && !event.getLocation().isBlank();
    }
}
