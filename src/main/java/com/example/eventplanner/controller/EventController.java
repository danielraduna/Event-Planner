package com.example.eventplanner.controller;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;
import com.example.eventplanner.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @PostMapping("/new")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
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

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestBody Event event) {
        eventService.deleteEvent(event);
        return ResponseEntity.ok().build();
    }
}
