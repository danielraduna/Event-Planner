package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.dto.LoginDto;
import com.example.eventplanner.exception.*;
import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.EventRequest;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.EventRepository;
import com.example.eventplanner.repository.EventRequestRepository;
import com.example.eventplanner.repository.FriendsGroupRepository;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final FriendsGroupRepository friendsGroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final JpaUserDetailsService userDetailsService;
    private final EventRequestRepository eventRequestRepository;
    @Override
    public User login(LoginDto loginDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        if (passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())) {
            return userRepository.findByUsernameEquals(loginDto.getUsername()).get();
        }
        throw new InvalidCredentialsException("Invalid username or password.");
    }

    @Override
    public User createUser(User user) {
        var u1 = userRepository.findByUsernameEquals(user.getUsername());
        if(u1.isPresent()) {
            throw new UserAlreadyExistsException("This username is already used!");
        }

        u1 = userRepository.findByEmail(user.getEmail());
        if(u1.isPresent()) {
            throw new UserAlreadyExistsException("This email is already used!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
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
        userRepository.save(user.get());
    }

    @Override
    public void assignUserToUser(Long idUser1, Long idUser2) {
        var user = userRepository.findById(idUser1);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found");
        }

        var friend = userRepository.findById(idUser2);
        if(friend.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found");
        }

        user.get().getFriends().add(friend.get());
        friend.get().getFriends().add(user.get());
        userRepository.save(user.get());
        userRepository.save(friend.get());
    }

    @Override
    public void assignUserToGroup(Long idUser, Long idGroup) {
        var user = userRepository.findById(idUser);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found");
        }

        var group = friendsGroupRepository.findById(idGroup);
        if(group.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found");
        }

        user.get().getFriendsGroups().add(group.get());
        group.get().getUsers().add(user.get());
        userRepository.save(user.get());
        friendsGroupRepository.save(group.get());
    }

    @Override
    public void deleteUserFromGroup(Long idUser, Long idGroup) {
        var user = userRepository.findById(idUser);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found");
        }

        var group = friendsGroupRepository.findById(idGroup);
        if(group.isEmpty()) {
            throw new GroupNotFoundException("Group with this id was not found");
        }

        user.get().getFriendsGroups().remove(group.get());
        group.get().getUsers().remove(user.get());
        userRepository.save(user.get());
        friendsGroupRepository.save(group.get());
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
    @Transactional
    @Override
    public void withdrawUserFromEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        event.getUsers().remove(user);
        user.getEvents().remove(event);

        eventRepository.save(event);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new UserNotFoundException("User with this id was not found");
        }
    }

    @Override
    public void sendEventRequest(Long senderId, Long receiverId, Long eventId){
        // Check if sender exists
        var sender = userRepository.findById(senderId);
        if (sender.isEmpty()) {
            throw new UserNotFoundException("Sender with this id was not found");
        }

        // Check if receiver exists
        var receiver = userRepository.findById(receiverId);
        if (receiver.isEmpty()) {
            throw new UserNotFoundException("Receiver with this id was not found");
        }

        // Check if event exists
        var event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new EventNotFoundException("Event with this id was not found");
        }

        // Check if the event request already exists
        var existingRequest = eventRequestRepository.findBySenderAndReceiverAndEvent(sender.get(), receiver.get(), event.get());
        if (existingRequest.isPresent()) {
            throw new EventRequestAlreadyExistsException("Event request already exists");
        }

        if(!event.get().getUsers().contains(sender.get())) {
            throw new SenderNotFoundInEventException("Sender is not in this event");
        }

        // Create a new event request
        EventRequest eventRequest = new EventRequest();
        eventRequest.setSender(sender.get());
        eventRequest.setReceiver(receiver.get());
        eventRequest.setEvent(event.get());
        eventRequest.setStatus(EventRequest.RequestStatus.PENDING);

        // Add event request to receiver's list of event requests
        receiver.get().getEventRequests().add(eventRequest);

        // Save the event request
        eventRequestRepository.save(eventRequest);

        // Save the receiver to update its list of event requests
        userRepository.save(receiver.get());
    }


}
