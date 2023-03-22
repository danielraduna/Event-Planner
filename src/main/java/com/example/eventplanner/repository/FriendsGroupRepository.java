package com.example.eventplanner.repository;

import com.example.eventplanner.model.FriendsGroup;
import com.example.eventplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendsGroupRepository extends JpaRepository<FriendsGroup, Long> {

    Optional<FriendsGroup> findByName(String name);

    Optional<FriendsGroup> findById(Long id);

    List<FriendsGroup> findByUsersContaining(User user);
}
