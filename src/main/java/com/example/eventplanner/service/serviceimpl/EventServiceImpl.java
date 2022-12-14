package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.exception.EventNotFoundException;
import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.EventRepository;
import com.example.eventplanner.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        var event = eventRepository.findById(id);
        if(event.isPresent()) {
            return event;
        }
        else {
            throw new EventNotFoundException("Event with this id was not found!");
        }
    }

    @Override
    public List<Event> getEventsByName(String name) {
        return eventRepository.findAllByNameEqualsIgnoreCase(name);
    }

    @Override
    public List<Event> getAllByAdmin(User user) {
        return eventRepository.findAllByAdmin(user);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        var e = eventRepository.findById(event.getId());
        if(e.isPresent()) {
            eventRepository.delete(event);
        }
        else {
            throw new EventNotFoundException("Event with this id was not found!");
        }
    }
}
