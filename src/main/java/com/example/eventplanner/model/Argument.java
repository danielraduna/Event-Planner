package com.example.eventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Argument {

    public enum ArgumentType {
        PRO, CONTRA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ArgumentType type;

    private String content;

    @ManyToOne
    @JsonIgnore
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
