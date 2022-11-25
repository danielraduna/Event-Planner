package com.example.eventplanner.repository;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Set<Event> findAllByNameEqualsIgnoreCase(String name);

    Set<Event> findAllByAdmin(User user);


}
