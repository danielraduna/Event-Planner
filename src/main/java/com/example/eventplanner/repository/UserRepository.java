package com.example.eventplanner.repository;

import com.example.eventplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> findByEmail(String email);

     Optional<User>  findByUsernameEqualsIgnoreCase(String username);

     Optional<User> findByUsernameEquals(String username);
}
