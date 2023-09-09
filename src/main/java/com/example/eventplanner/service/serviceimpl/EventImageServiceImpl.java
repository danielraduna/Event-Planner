package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.EventImage;
import com.example.eventplanner.model.User;
import com.example.eventplanner.repository.EventImageRepository;
import com.example.eventplanner.repository.EventRepository;
import com.example.eventplanner.service.EventImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class EventImageServiceImpl implements EventImageService {

    private final EventImageRepository eventImageRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public EventImage createEventImage(EventImage eventImage) {
        return eventImageRepository.save(eventImage); // Salvează doar evenimentul
    }

    @Override
    public List<EventImage> findAllByEventId(Long idEvent) {
        return eventImageRepository.findAllByEventId(idEvent);
    }

    @Override
    public void assignImageToEvent(Long idImage, Long idEvent) {
        var image = eventImageRepository.findById(idImage);
        var event = eventRepository.findById(idEvent);

        image.get().setEvent(event.get());
        event.get().getEventImages().add(image.get());

        eventImageRepository.save(image.get());
        eventRepository.save(event.get());
    }
}
