package com.example.eventplanner.controller;

import com.example.eventplanner.dto.LoginDto;
import com.example.eventplanner.model.User;
import com.example.eventplanner.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @PostMapping("/{eventId}/{userId}/withdraw")
    public ResponseEntity<Void> withdrawFromEvent(@PathVariable Long eventId, @PathVariable Long userId) {
        userService.withdrawUserFromEvent(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        userService.updateUser(updatedUser);
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

    @PostMapping("/sendFriendRequest")
    public ResponseEntity<Void> sendFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
        userService.sendFriendRequest(senderId, receiverId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<Set<User>> getUserFriends(@PathVariable Long userId) {
        Set<User> friends = userService.getUserById(userId).get().getFriends();
        return ResponseEntity.ok(friends);
    }

    @PutMapping("/assignToPoll")
    public ResponseEntity<User> assignUserToPoll(@RequestParam Long idUser, @RequestParam Long idPoll) {
        userService.assignUserToPoll(idUser, idPoll);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unfriend")
    public ResponseEntity<Void> unfriend(@RequestParam Long idSender, @RequestParam Long idReceiver) {
        userService.unfriend(idSender, idReceiver);
        return ResponseEntity.ok().build();
    }

}
