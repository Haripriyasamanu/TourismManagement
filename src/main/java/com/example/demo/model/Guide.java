package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data @Builder @ToString
public class Guide {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long placeId;
    private String name;
    private String contactNumber;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;
}
