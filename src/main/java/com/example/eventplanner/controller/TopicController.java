package com.example.eventplanner.controller;

import com.example.eventplanner.model.Topic;
import com.example.eventplanner.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@AllArgsConstructor
public class TopicController {

    private final TopicService topicService;

    // Obține toate topicurile pentru un eveniment specificat
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Topic>> getAllTopicsByEventId(@PathVariable Long eventId) {
        return ResponseEntity.ok(topicService.findAllByEventId(eventId));
    }

    // Creează un topic
    @PostMapping("/new")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        return ResponseEntity.ok(topicService.createTopic(topic));
    }

    // Obține detalii despre un topic specific
    @GetMapping("/{topicId}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long topicId) {
        return ResponseEntity.of(topicService.findById(topicId));
    }

    // Șterge un topic
    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long topicId) {
        topicService.deleteById(topicId);
        return ResponseEntity.ok().build();
    }
}
