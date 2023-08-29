package com.example.eventplanner.service;

import com.example.eventplanner.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    List<Topic> findAllByEventId(Long eventId);

    Optional<Topic> findById(Long topicId);

    Topic createTopic(Topic topic);

    void deleteById(Long topicId);
}
