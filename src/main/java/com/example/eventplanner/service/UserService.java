package com.example.eventplanner.service;

import com.example.eventplanner.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    void createUser(User user);

    Set<User> getAllUsers();

    Optional<User>  getUserById(Long id);

    Optional<User>  getUserByEmail(String email);

    Optional<User>  getuserByUsername(String username);
}
