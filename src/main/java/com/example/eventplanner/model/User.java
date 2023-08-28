package com.example.eventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "pass")
    private String password;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;



    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends;

    // Cererile de prietenie trimise
    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Set<FriendRequest> sentRequests;

    // Cererile de prietenie primite
    @JsonIgnore
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<FriendRequest> receivedRequests;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private Set<FriendsGroup> friendsGroups;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_picture_id", referencedColumnName = "id")
    private ProfilePicture profilePicture;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private Set<EventRequest> eventRequests;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_poll",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "poll_id")
    )
    private Set<Poll> votedPolls;
}
