package com.example.eventplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.cj.jdbc.Clob;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String imageData;

    @OneToOne(mappedBy = "profilePicture")
    @JsonIgnore
    private User user;
}
