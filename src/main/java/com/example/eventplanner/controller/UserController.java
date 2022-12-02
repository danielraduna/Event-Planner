package com.example.eventplanner.controller;

import com.example.eventplanner.model.User;
import com.example.eventplanner.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().
                body(userService.getUserById(id).get());
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().
                body(userService.getUserByUsername(username).get());
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok().
                body(userService.getUserByEmail(email).get());
    }

    @PutMapping("/assignToEvent")
    public ResponseEntity<User> assignUserToEvent(@RequestParam Long idUser, @RequestParam Long idEvent) {
        userService.assignUserToEvent(idUser, idEvent);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<User> updadteUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }
}
