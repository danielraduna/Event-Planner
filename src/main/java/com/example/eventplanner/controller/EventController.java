package com.example.eventplanner.controller;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;
import com.example.eventplanner.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:4200",  allowedHeaders = "*")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/new")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        event.setCreateDate(LocalDateTime.now());

        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @GetMapping("/list")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/public-events")
    public List<Event> getAllPublicEvents() {
        return eventService.getPublicEvents();
    }



    @GetMapping("/byId/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok().
                body(eventService.getEventById(id).get());
    }

    @GetMapping("/byName/{name}")
    public List<Event> getEventsByName(@PathVariable String name) {
        return eventService.getEventsByName(name);
    }

    @GetMapping("/byUser/{idUser}")
    public List<Event> getEventsByUser(@PathVariable Long idUser) {
        return eventService.getAllByUser(idUser);
    }

    @PutMapping("/update")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{idEvent}")
    public ResponseEntity<User> deleteUser(@PathVariable Long idEvent) {
        eventService.deleteEvent(idEvent);
        return ResponseEntity.ok().build();
    }
}
