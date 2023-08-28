package com.example.eventplanner.controller;

import com.example.eventplanner.model.Poll;
import com.example.eventplanner.service.PollService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
@AllArgsConstructor
public class PollController {

    private final PollService pollService;

    @PostMapping("/new")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = pollService.createPoll(poll);
        return ResponseEntity.ok(createdPoll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Long id) {
        Poll poll = pollService.getPollById(id);
        if (poll != null) {
            return ResponseEntity.ok(poll);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Poll>> getPollsByEventId(@PathVariable Long eventId) {
        List<Poll> polls = pollService.getPollsByEventId(eventId);
        return ResponseEntity.ok(polls);
    }

    @PutMapping
    public ResponseEntity<Poll> updatePoll(@RequestBody Poll poll) {
        Poll updatedPoll = pollService.updatePoll(poll);
        return ResponseEntity.ok(updatedPoll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/vote/{optionIndex}")
    public ResponseEntity<Void> voteForOption(@PathVariable Long id, @PathVariable int optionIndex) {
        try {
            pollService.voteForOption(id, optionIndex);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
