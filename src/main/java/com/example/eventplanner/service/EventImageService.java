package com.example.eventplanner.service;

import com.example.eventplanner.model.EventImage;

import java.util.List;

public interface EventImageService {

    public EventImage createEventImage(EventImage eventImage);
    public List<EventImage> findAllByEventId(Long idEvent);

    public void assignImageToEvent(Long idImage, Long idEvent);
}
