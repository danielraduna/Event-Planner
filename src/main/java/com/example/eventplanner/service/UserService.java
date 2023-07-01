package com.example.eventplanner.service;

import com.example.eventplanner.dto.LoginDto;
import com.example.eventplanner.exception.EventRequestAlreadyExistsException;
import com.example.eventplanner.model.EventRequest;
import com.example.eventplanner.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    Optional<User>  getUserById(Long id);

    Optional<User>  getUserByEmail(String email);

    Optional<User>  getUserByUsername(String username);
    User  login(LoginDto loginDto);

    void assignUserToEvent(Long idUser, Long idEvent);

    void assignUserToUser(Long idUser1, Long idUser2);

    void assignUserToGroup(Long idUser, Long idGroup);

    void deleteUserFromGroup(Long idUser, Long idGroup);
    void updateUser(User user);

    void makeUserAdminOfEvent(Long idUser, Long idEvent);
    public void deleteUserById(Long id);

    void sendEventRequest(Long senderId, Long receiverId, Long eventId) throws EventRequestAlreadyExistsException;

}
