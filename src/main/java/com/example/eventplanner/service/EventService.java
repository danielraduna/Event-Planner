package com.example.eventplanner.service;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;

import java.util.Optional;
import java.util.Set;

public interface EventService {

    void createEvent(Event Event);

    Set<Event> getAllEvents();

    Optional<Event> getEventById(Long id);

    Set<Event> getEventsByName(String name);

    Set<Event> getAllByAdmin(User user);

}
