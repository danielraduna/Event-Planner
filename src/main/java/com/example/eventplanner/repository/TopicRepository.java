package com.example.eventplanner.repository;

import com.example.eventplanner.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByEvent_Id(Long eventId);
}
