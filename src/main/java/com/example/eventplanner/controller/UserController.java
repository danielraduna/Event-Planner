package com.example.eventplanner.controller;

import com.example.eventplanner.dto.LoginDto;
import com.example.eventplanner.model.User;
import com.example.eventplanner.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
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

    @PutMapping("/assignToUser")
    public ResponseEntity<User> assignUserToUser(@RequestParam Long idUser1, @RequestParam Long idUser2) {
        userService.assignUserToUser(idUser1, idUser2);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assignToGroup")
    public ResponseEntity<User> assignUserToGroup(@RequestParam Long idUser, @RequestParam Long idGroup) {
        userService.assignUserToGroup(idUser, idGroup);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/deleteFromGroup")
    public ResponseEntity<User> deleteUserFromGroup(@RequestParam Long idUser, @RequestParam Long idGroup) {
        userService.deleteUserFromGroup(idUser, idGroup);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/makeAdminOfEvent")
    public ResponseEntity<User> makeAdminOfEvent(@RequestParam Long idUser, @RequestParam Long idEvent) {
        userService.makeUserAdminOfEvent(idUser, idEvent);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<User> updadteUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/login")
    public User login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/sendEventRequest")
    public ResponseEntity<User> sendEventRequest(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam Long eventId) {
        userService.sendEventRequest(senderId, receiverId, eventId);
        return ResponseEntity.ok().build();
    }

}
