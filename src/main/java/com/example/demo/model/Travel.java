package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor @Data @Builder @ToString
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long placeId;
    private String type;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;
    private String day;
    private String timings;

}
