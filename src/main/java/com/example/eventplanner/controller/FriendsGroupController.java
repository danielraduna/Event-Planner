package com.example.eventplanner.controller;

import com.example.eventplanner.model.FriendsGroup;
import com.example.eventplanner.service.FriendsGroupService;
import com.example.eventplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/groups")
public class FriendsGroupController {
    private final FriendsGroupService friendsGroupService;
    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<FriendsGroup> createGroup(@RequestBody FriendsGroup group) {
        friendsGroupService.createGroup(group);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<FriendsGroup> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(friendsGroupService.getGroupById(id).get());
    }

    @GetMapping("/byUser/{id}")
    public List<FriendsGroup> getGroupsByUser(@PathVariable Long id) {
        return friendsGroupService.getGroupsOfUser(userService.getUserById(id).get());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<FriendsGroup> deleteGroup(@RequestBody FriendsGroup group) {
        friendsGroupService.deleteGroup(group);
        return ResponseEntity.ok().build();
    }
}
