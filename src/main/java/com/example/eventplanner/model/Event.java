package com.example.eventplanner.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private LocalDateTime eventDate;

    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User admin;

}
