package com.example.eventplanner.service;

import com.example.eventplanner.model.Argument;

import java.util.List;
import java.util.Optional;

public interface ArgumentService {

    List<Argument> findAllByTopicId(Long topicId);

    Optional<Argument> findById(Long argumentId);

    public void assignArgumenttoTopic(Long argumentId, Long topicId);

    Argument createArgument(Argument argument);

    void deleteById(Long argumentId);
}
