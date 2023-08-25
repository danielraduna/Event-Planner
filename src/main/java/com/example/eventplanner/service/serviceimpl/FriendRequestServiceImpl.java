package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.FriendRequest;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.FriendRequestRepository;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.FriendRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    public List<FriendRequest> getReceivedFriendRequests(Long userId) {
        return friendRequestRepository.findByReceiverId(userId);
    }

    public void deleteFriendRequest(Long id) {
        friendRequestRepository.deleteById(id);
    }

    public void acceptFriendRequest(Long requestId) {
        Optional<FriendRequest> optionalFriendRequest = friendRequestRepository.findById(requestId);

        if (optionalFriendRequest.isPresent()) {
            FriendRequest friendRequest = optionalFriendRequest.get();
            User sender = friendRequest.getSender();
            User receiver = friendRequest.getReceiver();

            sender.getFriends().add(receiver);
            receiver.getFriends().add(sender);

            userRepository.save(sender);
            userRepository.save(receiver);

            friendRequestRepository.deleteById(requestId);
        } else {
            throw new RuntimeException("Friend request not found!");
        }
    }

    public void rejectFriendRequest(Long requestId) {
        if (friendRequestRepository.existsById(requestId)) {
            friendRequestRepository.deleteById(requestId);
        } else {
            throw new RuntimeException("Friend request not found!");
        }
    }
}
