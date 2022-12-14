package com.example.eventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;

    private String firstName;

    private String lastName;

    @Column(unique=true)
    private String email;

    private String phone;

    private LocalDate birthday;

    @JsonIgnore
    @OneToMany(mappedBy = "admin") // -> unidirectional relantionship -> creates a join table
    private Set<Event> eventsAdmin;

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events;
}
