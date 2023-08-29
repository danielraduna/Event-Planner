package com.example.eventplanner.repository;

import com.example.eventplanner.model.Argument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArgumentRepository extends JpaRepository<Argument, Long> {

    List<Argument> findByTopic_Id(Long topicId);

}
