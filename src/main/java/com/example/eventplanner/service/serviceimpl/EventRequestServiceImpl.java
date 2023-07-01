package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.EventRequest;
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

    @Override
    public void acceptRequest(Long requestId) {
        EventRequest request = eventRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + requestId));

        request.getReceiver().getEvents().add(request.getEvent());
        userRepository.save(request.getReceiver());

        eventRequestRepository.deleteById(requestId);
    }

    @Override
    public void rejectRequest(Long requestId) {
        eventRequestRepository.deleteById(requestId);
    }

    @Override
    public List<EventRequest> getAllEventRequests() {
        return eventRequestRepository.findAll();
    }

}
