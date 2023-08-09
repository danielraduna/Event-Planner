package com.example.eventplanner.service;


import com.example.eventplanner.model.EventRequest;
import com.example.eventplanner.model.User;

import java.util.List;

public interface EventRequestService {

    List<EventRequest> getAllEventRequests();

    public void acceptRequest(Long requestId);
    public void rejectRequest(Long requestId);

    public List<EventRequest> getEventRequestsByUserId(Long userId);

    public List<EventRequest> getPendingEventRequestsByUserId(Long userId);

    public List<User> getUsersThatRejectedEvent(Long eventId);
}
