package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.Topic;
import com.example.eventplanner.repository.TopicRepository;
import com.example.eventplanner.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public List<Topic> findAllByEventId(Long eventId) {
        return topicRepository.findByEvent_Id(eventId);
    }

    @Override
    public Optional<Topic> findById(Long topicId) {
        return topicRepository.findById(topicId);
    }

    @Override
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void deleteById(Long topicId) {
        topicRepository.deleteById(topicId);
    }
}
