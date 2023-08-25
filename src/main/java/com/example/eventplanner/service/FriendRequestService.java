package com.example.eventplanner.service;

import com.example.eventplanner.model.FriendRequest;

import java.util.List;

public interface FriendRequestService {

    public List<FriendRequest> getReceivedFriendRequests(Long userId);

    public void deleteFriendRequest(Long id);

    public void acceptFriendRequest(Long requestId);

    public void rejectFriendRequest(Long requestId);
}
