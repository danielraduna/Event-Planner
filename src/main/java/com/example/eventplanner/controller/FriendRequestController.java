package com.example.eventplanner.controller;

import com.example.eventplanner.model.FriendRequest;
import com.example.eventplanner.repository.FriendRequestRepository;
import com.example.eventplanner.service.FriendRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friend-requests")
@AllArgsConstructor
public class FriendRequestController {

    private final FriendRequestService friendRequestService;
    private final FriendRequestRepository friendRequestRepository;


    // Preia cererile de prietenie primite de un utilizator
    @GetMapping("/received/{userId}")
    public ResponseEntity<List<FriendRequest>> getReceivedFriendRequests(@PathVariable Long userId) {
        List<FriendRequest> friendRequests = friendRequestService.getReceivedFriendRequests(userId);
        return new ResponseEntity<>(friendRequests, HttpStatus.OK);
    }

    @GetMapping("/existFriendRequest")
    public boolean existFriendRequest(@RequestParam Long idSender, @RequestParam Long idReceiver) {
        return friendRequestRepository.existsBySenderIdAndReceiverId(idSender, idReceiver);
    }

    // Acceptă o cerere de prietenie
    @PostMapping("/accept/{requestId}")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable Long requestId) {
        friendRequestService.acceptFriendRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Respinge o cerere de prietenie
    @PostMapping("/reject/{requestId}")
    public ResponseEntity<Void> rejectFriendRequest(@PathVariable Long requestId) {
        friendRequestService.rejectFriendRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Șterge o cerere de prietenie (acest endpoint ar putea fi folosit pentru a retrage o cerere trimisă)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable Long id) {
        friendRequestService.deleteFriendRequest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

