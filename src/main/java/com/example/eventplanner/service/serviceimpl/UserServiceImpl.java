package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        return;
    }

    @Override
    public Set<User> getAllUsers() {
        return null;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return null;
    }

    @Override
    public Optional<User>  getUserByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User>  getuserByUsername(String username) {
        return null;
    }
}
