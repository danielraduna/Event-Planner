package com.example.eventplanner.repository;

import com.example.eventplanner.model.Event;
import com.example.eventplanner.model.EventRequest;
import com.example.eventplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {

    List<EventRequest> findByReceiver(User receiver);
    Optional<EventRequest> findByReceiverAndEvent(User receiver, Event event);
    Optional<EventRequest> findBySenderAndReceiverAndEvent(User sender, User receiver, Event event);

    List<EventRequest> findByReceiverAndStatus(User receiver, EventRequest.RequestStatus status);

    @Query("SELECT e.receiver FROM EventRequest e WHERE e.event.id = :eventId AND e.status = 'REJECTED'")
    List<User> findUsersThatRejectedEvent(Long eventId);


}
