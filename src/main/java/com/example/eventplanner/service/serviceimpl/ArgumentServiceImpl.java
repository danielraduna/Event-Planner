package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.Argument;
import com.example.eventplanner.model.Topic;
import com.example.eventplanner.repository.ArgumentRepository;
import com.example.eventplanner.repository.TopicRepository;
import com.example.eventplanner.service.ArgumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArgumentServiceImpl implements ArgumentService {

    private final ArgumentRepository argumentRepository;
    private final TopicRepository topicRepository;
    @Override
    public List<Argument> findAllByTopicId(Long topicId) {
        return argumentRepository.findByTopic_Id(topicId);
    }

    @Override
    public Optional<Argument> findById(Long argumentId) {
        return argumentRepository.findById(argumentId);
    }

    @Override
    public void assignArgumenttoTopic(Long argumentId, Long topicId) {
        Argument argument = argumentRepository.findById(argumentId)
                .orElseThrow(() -> new RuntimeException("Argument not found with id " + argumentId));
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found with id " + topicId));

        argument.setTopic(topic);
        topic.getArguments().add(argument);

        argumentRepository.save(argument);
        topicRepository.save(topic);
    }
    @Override
    public Argument createArgument(Argument argument) {  // Metoda redenumită
        return argumentRepository.save(argument);
    }

    @Override
    public void deleteById(Long argumentId) {
        argumentRepository.deleteById(argumentId);
    }
}
