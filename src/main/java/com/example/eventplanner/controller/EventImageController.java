package com.example.eventplanner.controller;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.EventImage;
import com.example.eventplanner.service.EventImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/eventImages")
@CrossOrigin(origins = "http://localhost:4200/")
public class EventImageController {

    private final EventImageService eventImageService;


    @PostMapping("/new")
    public ResponseEntity<EventImage> createEvent(@RequestBody EventImage eventImage) {
        return ResponseEntity.ok(eventImageService.createEventImage(eventImage));
    }

    @GetMapping("/byEvent")
    public ResponseEntity<List<EventImage>> getEventImagesByEvent(@RequestParam Long idEvent) {
        return ResponseEntity.ok(eventImageService.findAllByEventId(idEvent));
    }

    @PutMapping("/assignImageToEvent")
    public void assignImageToEvent(@RequestParam Long idImage, @RequestParam Long idEvent) {
        eventImageService.assignImageToEvent(idImage, idEvent);
    }
}
