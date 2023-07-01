package com.example.eventplanner.service;


import com.example.eventplanner.model.EventRequest;

import java.util.List;

public interface EventRequestService {

    List<EventRequest> getAllEventRequests();

    public void acceptRequest(Long requestId);
    public void rejectRequest(Long requestId);
}
