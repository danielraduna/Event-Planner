package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.exception.GroupNotFoundException;
import com.example.eventplanner.exception.UserNotFoundException;
import com.example.eventplanner.model.FriendsGroup;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.FriendsGroupRepository;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.FriendsGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FriendsGroupServiceImpl implements FriendsGroupService {

    private final FriendsGroupRepository friendsGroupRepository;
    private final UserRepository userRepository;

    @Override
    public void createGroup(FriendsGroup group) {
        friendsGroupRepository.save(group);
    }

    @Override
    public Optional<FriendsGroup> getGroupById(Long id) {
        var group = friendsGroupRepository.findById(id);
        if(group.isEmpty()) {
            throw new GroupNotFoundException("Group with this id was not found!");
        }
        return group;
    }

    @Override
    public List<FriendsGroup> getGroupsOfUser(User user) {
        var u = userRepository.findById(user.getId());
        if(u.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found!");
        }

        return friendsGroupRepository.findByUsersContaining(user);
    }

    @Override
    public void deleteGroup(FriendsGroup group) {
        var g = friendsGroupRepository.findById(group.getId());
        if(g.isEmpty()) {
            throw new GroupNotFoundException("Group with this id was not found!");
        }
        friendsGroupRepository.delete(group);
    }
}
