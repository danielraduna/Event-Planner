package com.example.eventplanner.service;

import com.example.eventplanner.model.FriendsGroup;
import com.example.eventplanner.model.User;

import java.util.List;
import java.util.Optional;

public interface FriendsGroupService {

    void createGroup(FriendsGroup group);

    Optional<FriendsGroup> getGroupById(Long id);

    List<FriendsGroup> getGroupsOfUser(User user);

    void deleteGroup(FriendsGroup group);
}
