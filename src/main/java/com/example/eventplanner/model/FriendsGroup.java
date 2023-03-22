package com.example.eventplanner.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class FriendsGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long numberParticipants;

    private String description;

    @ManyToMany
    @JoinTable(name = "friends_groups_users",
            joinColumns = @JoinColumn(name = "friends_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;
}
