package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor @Data @Builder @ToString
public class Restaurant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long placeId;
    private String name;
    private String rating;
    private String description;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;
    private String foodType;
    private String inTime;
    private String outTime;
    private String location;
}
