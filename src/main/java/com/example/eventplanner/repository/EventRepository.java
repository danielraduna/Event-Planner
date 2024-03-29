package com.example.eventplanner.repository;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByNameEqualsIgnoreCase(String name);

    List<Event> findByUsersId(@Param("userId") Long userId);

    List<Event> findByType(Event.EventType type);

}
