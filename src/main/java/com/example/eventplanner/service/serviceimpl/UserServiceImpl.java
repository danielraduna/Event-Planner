package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.dto.LoginDto;
import com.example.eventplanner.exception.*;
import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.EventRequest;
import com.example.eventplanner.model.FriendRequest;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.*;
import com.example.eventplanner.service.EmailService;
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

    private final FriendRequestRepository friendRequestRepository;

    private final PollRepository pollRepository;

    private final EmailService emailService;

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
        var sender = userRepository.findById(senderId);
        if (sender.isEmpty()) {
            throw new UserNotFoundException("Sender with this id was not found");
        }

        var receiver = userRepository.findById(receiverId);
        if (receiver.isEmpty()) {
            throw new UserNotFoundException("Receiver with this id was not found");
        }

        var event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new EventNotFoundException("Event with this id was not found");
        }

        if(!event.get().getUsers().contains(sender.get())) {
            throw new SenderNotFoundInEventException("Sender is not in this event");
        }

        EventRequest eventRequest = new EventRequest();
        eventRequest.setSender(sender.get());
        eventRequest.setReceiver(receiver.get());
        eventRequest.setEvent(event.get());
        eventRequest.setStatus(EventRequest.RequestStatus.PENDING);

        receiver.get().getEventRequests().add(eventRequest);

        eventRequestRepository.save(eventRequest);

        userRepository.save(receiver.get());

        emailService.sendEventInvitation(receiver.get(), sender.get(), event.get(), "eventplanner928@gmail.com");
    }

    public void sendFriendRequest(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new UserNotFoundException("Receiver not found"));

        if(friendRequestRepository.existsBySenderIdAndReceiverId(senderId, receiverId)) {
            throw new IllegalArgumentException("Friend request already exists and is pending.");
        }

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);

        friendRequestRepository.save(friendRequest);
    }

    public void assignUserToPoll(Long idUser, Long idPoll) {
        var user = userRepository.findById(idUser);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with this id was not found");
        }

        var poll = pollRepository.findById(idPoll);
        if(poll.isEmpty()) {
            throw new EventNotFoundException("Event with this id was not found");

        }

        poll.get().getVoters().add(user.get());
        user.get().getVotedPolls().add(poll.get());
        pollRepository.save(poll.get());
        userRepository.save(user.get());
    }

    public void unfriend(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new UserNotFoundException("Receiver not found"));

        if(sender.getFriends().contains(receiver)) {
            sender.getFriends().remove(receiver);
            receiver.getFriends().remove(sender);
            userRepository.save(receiver);
            userRepository.save(sender);
        }
    }
}
