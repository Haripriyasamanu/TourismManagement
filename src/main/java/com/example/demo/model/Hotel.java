package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor@NoArgsConstructor @Data
@ToString @Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long placeId;
    private String name;
    private String type;
    private String rating;
    private String description;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;
    private String inTime;
    private String outTime;
    private String location;
}
