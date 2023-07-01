package com.example.eventplanner.controller;

import com.example.eventplanner.model.EventRequest;
import com.example.eventplanner.service.EventRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/eventrequests")
@CrossOrigin(origins = "http://localhost:4200/")
public class EventRequestController {

    private final EventRequestService eventRequestService;

    @GetMapping("/list")
    public ResponseEntity<List<EventRequest>> getAllEventRequests() {
        return ResponseEntity.ok(eventRequestService.getAllEventRequests());
    }


    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(@PathVariable Long requestId) {
        try {
            eventRequestService.acceptRequest(requestId);
            return ResponseEntity.ok("Request accepted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId) {
        try {
            eventRequestService.rejectRequest(requestId);
            return ResponseEntity.ok("Request rejected successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
