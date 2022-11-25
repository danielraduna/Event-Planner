package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.EventRepository;
import com.example.eventplanner.service.EventService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Event Event) {

    }

    @Override
    public Set<Event> getAllEvents() {
        return null;
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Event> getEventsByName(String name) {
        return null;
    }

    @Override
    public Set<Event> getAllByAdmin(User user) {
        return null;
    }
}
