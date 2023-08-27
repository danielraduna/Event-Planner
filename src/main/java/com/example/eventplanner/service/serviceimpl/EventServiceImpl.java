package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.exception.EventNotFoundException;
import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.EventRepository;
import com.example.eventplanner.repository.UserRepository;
import com.example.eventplanner.service.EventService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public Event createEvent(Event event) {
        User user = event.getUsers().get(0);
        if(user.getId() == null) {
            throw new RuntimeException("User not found!");
        }
        if(user.getEvents() == null) {
            user.setEvents(new HashSet<>());
        }
        user.getEvents().add(event);
        return eventRepository.save(event); // Salvează doar evenimentul
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
    public List<Event> getAllByUser(Long idUser) {
        return eventRepository.findByUsersId(idUser);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long idEvent) {
        var event = eventRepository.findById(idEvent);
        if(event.isPresent()) {
            eventRepository.delete(event.get());
        }
        else {
            throw new EventNotFoundException("Event with this id was not found!");
        }
    }
}
