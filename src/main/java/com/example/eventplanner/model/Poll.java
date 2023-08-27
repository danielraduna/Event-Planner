package com.example.eventplanner.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Poll {

    public enum PollType {
        SINGLE_CHOICE,
        MULTIPLE_CHOICE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PollType pollType;

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private List<Integer> votes;

}
