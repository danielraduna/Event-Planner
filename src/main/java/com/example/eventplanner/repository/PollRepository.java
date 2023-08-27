package com.example.eventplanner.repository;

import com.example.eventplanner.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByEvent_Id(Long eventId);
}
