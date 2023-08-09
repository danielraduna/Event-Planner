package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.exception.EventNotFoundException;
import com.example.eventplanner.exception.UserNotFoundException;
import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.EventRequest;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.EventRepository;
import com.example.eventplanner.repository.EventRequestRepository;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.EventRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventRequestServiceImpl implements EventRequestService {

    private final EventRequestRepository eventRequestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public void acceptRequest(Long requestId) {
        EventRequest request = eventRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + requestId));

        request.getReceiver().getEvents().add(request.getEvent());
        userRepository.save(request.getReceiver());

        request.setStatus(EventRequest.RequestStatus.ACCEPTED);
        eventRequestRepository.save(request);
    }

    @Override
    public void rejectRequest(Long requestId) {
        EventRequest request = eventRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + requestId));

        request.setStatus(EventRequest.RequestStatus.REJECTED);
        eventRequestRepository.save(request);
    }

    @Override
    public List<EventRequest> getEventRequestsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return eventRequestRepository.findByReceiver(user);
    }

    @Override
    public List<EventRequest> getAllEventRequests() {
        return eventRequestRepository.findAll();
    }

    @Override
    public List<EventRequest> getPendingEventRequestsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return eventRequestRepository.findByReceiverAndStatus(user, EventRequest.RequestStatus.PENDING);
    }

    public List<User> getUsersThatRejectedEvent(Long eventId) {
        return eventRequestRepository.findUsersThatRejectedEvent(eventId);
    }




}
