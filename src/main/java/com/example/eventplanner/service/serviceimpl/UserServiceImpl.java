package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.exception.EventNotFoundException;
import com.example.eventplanner.exception.UserAlreadyExistsException;
import com.example.eventplanner.exception.UserNotFoundException;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.EventRepository;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public UserServiceImpl(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void createUser(User user) {
        var u1 = userRepository.findByUsernameEquals(user.getUsername());
        if(u1.isPresent()) {
            throw new UserAlreadyExistsException("This username is already used!");
        }

        u1 = userRepository.findByEmail(user.getEmail());
        if(u1.isPresent()) {
            throw new UserAlreadyExistsException("This email is already used!");
        }
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
            throw new UserNotFoundException("User with this id was not found");
        }
    }

    @Override
    public Optional<User>  getUserByEmail(String email) {
        var user =  userRepository.findByEmail(email);
        if(user.isPresent()) {
            return user;
        }
        else {
            throw new UserNotFoundException("User with this email was not found");
        }
    }

    @Override
    public Optional<User>  getUserByUsername(String username) {
        var user =  userRepository.findByUsernameEqualsIgnoreCase(username);
        if(user.isPresent()) {
            return user;
        }
        else {
            throw new UserNotFoundException("User with this username was not found");
        }
    }

    @Override
    public void assignUserToEvent(Long idUser, Long idEvent) {
        var user = userRepository.findById(idUser);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found");
        }

        var event = eventRepository.findById(idEvent);
        if(event.isEmpty()) {
            throw new EventNotFoundException("Event with this id was not found");

        }

        event.get().getUsers().add(user.get());
        user.get().getEvents().add(event.get());
        eventRepository.save(event.get());
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void makeUserAdminOfEvent(Long idUser, Long idEvent) {
        var user = userRepository.findById(idUser);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found");
        }

        var event = eventRepository.findById(idEvent);
        if(event.isEmpty()) {
            throw new EventNotFoundException("Event with this id was not found");
        }

        event.get().setAdmin(user.get());
        eventRepository.save(event.get());

        user.get().getEventsAdmin().add(event.get());
        user.get().getEvents().add(event.get());
        userRepository.save(user.get());

    }

    @Override
    public void deleteUser(User user) {
        var u = userRepository.findById(user.getId());
        if(u.isPresent()) {
            userRepository.delete(user);
        }
        else {
            throw new UserNotFoundException("User with this id was not found");
        }
    }
}
