package com.example.eventplanner.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    public enum EventType {
        PRIVATE,
        PUBLIC
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @Column(length = 1000)
    private String description;

    private LocalDateTime startDate;

    private LocalDateTime stopDate;

    private LocalDateTime createDate;

    private String startTime;

    private String endTime;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @ElementCollection
    private List<String> extraDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User admin;

    @ManyToMany(mappedBy = "events")
    private List<User> users;


    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<EventRequest> eventRequests;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Poll> polls;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<EventImage> eventImages;
}
