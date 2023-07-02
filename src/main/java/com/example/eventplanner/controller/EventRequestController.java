package com.example.eventplanner.controller;

import com.example.eventplanner.model.EventRequest;
import com.example.eventplanner.service.EventRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> acceptRequest(@PathVariable Long requestId) {
        Map<String, String> response = new HashMap<>();
        try {
            eventRequestService.acceptRequest(requestId);
            response.put("message", "Request accepted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<Map<String, String>> rejectRequest(@PathVariable Long requestId) {
        Map<String, String> response = new HashMap<>();
        try {
            eventRequestService.rejectRequest(requestId);
            response.put("message", "Request rejected successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/received-invitations/{userId}")
    public ResponseEntity<List<EventRequest>> getReceivedEventRequests(@PathVariable Long userId) {
        List<EventRequest> eventRequests = eventRequestService.getEventRequestsByUserId(userId);
        return ResponseEntity.ok(eventRequests);
    }
}
