package com.example.eventplanner.repository;

import com.example.eventplanner.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findBySenderId(Long userId);

    List<FriendRequest> findByReceiverId(Long userId);

    boolean existsBySenderIdAndReceiverId(Long senderId, Long receiverId);

}
