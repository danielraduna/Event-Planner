package com.example.eventplanner.service;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;

import java.util.List;
import java.util.Optional;

public interface EventService {

    void createEvent(Event event);

    List<Event> getAllEvents();

    Optional<Event> getEventById(Long id);

    List<Event> getEventsByName(String name);

    List<Event> getAllByUser(Long idUser);

    void updateEvent(Event event);

    void deleteEvent(Event event);
}
