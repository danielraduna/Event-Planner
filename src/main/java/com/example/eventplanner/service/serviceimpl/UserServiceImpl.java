package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.exception.UserNotFoundException;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        var user = userRepository.findById(id);
        if(user.isPresent()) {
            return user;
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public Optional<User>  getUserByEmail(String email) {
        var user =  userRepository.findByEmail(email);
        if(user.isPresent()) {
            return user;
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public Optional<User>  getUserByUsername(String username) {
        var user =  userRepository.findByUsernameEqualsIgnoreCase(username);
        if(user.isPresent()) {
            return user;
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void deleteUser(User user) {
        var u = userRepository.findById(user.getId());
        if(u.isPresent()) {
            userRepository.delete(user);
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }
}
