package com.example.eventplanner.service;

import com.example.eventplanner.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    List<User> getAllUsers();

    Optional<User>  getUserById(Long id);

    Optional<User>  getUserByEmail(String email);

    Optional<User>  getUserByUsername(String username);

    void deleteUser(User user);
}
