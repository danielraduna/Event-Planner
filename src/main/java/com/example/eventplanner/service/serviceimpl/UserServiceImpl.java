package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
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
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User>  getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User>  getUserByUsername(String username) {
        return userRepository.findByUsernameEqualsIgnoreCase(username);
    }
}
