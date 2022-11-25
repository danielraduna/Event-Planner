package com.example.eventplanner.service;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EventService {

    void createEvent(Event Event);

    List<Event> getAllEvents();

    Optional<Event> getEventById(Long id);

    List<Event> getEventsByName(String name);

    List<Event> getAllByAdmin(User user);

}
