package com.example.eventplanner.controller;

import com.example.eventplanner.model.Argument;
import com.example.eventplanner.service.ArgumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arguments")
@AllArgsConstructor
public class ArgumentController {

    private ArgumentService argumentService;

    // Obține toate argumentele pentru un topic specificat
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<Argument>> getAllArgumentsByTopicId(@PathVariable Long topicId) {
        return ResponseEntity.ok(argumentService.findAllByTopicId(topicId));
    }

    // Creează un argument
    @PostMapping("/new")
    public ResponseEntity<Argument> createArgument(@RequestBody Argument argument) {
        return ResponseEntity.ok(argumentService.createArgument(argument));
    }

    @PostMapping("/{argumentId}/assign-to-topic/{topicId}")
    public ResponseEntity<Void> assignArgumentToTopic(
            @PathVariable Long argumentId,
            @PathVariable Long topicId) {
        try {
            argumentService.assignArgumenttoTopic(argumentId, topicId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obține detalii despre un argument specific
    @GetMapping("/{argumentId}")
    public ResponseEntity<Argument> getArgumentById(@PathVariable Long argumentId) {
        return ResponseEntity.of(argumentService.findById(argumentId));
    }

    // Șterge un argument
    @DeleteMapping("/{argumentId}")
    public ResponseEntity<Void> deleteArgument(@PathVariable Long argumentId) {
        argumentService.deleteById(argumentId);
        return ResponseEntity.ok().build();
    }
}
