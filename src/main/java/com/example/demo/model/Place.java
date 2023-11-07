package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor @ToString @Data @Builder
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String history;
    private String location;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    Set<Hotel> hotels= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    Set<Restaurant> restaurants= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    Set<Travel> travels=new HashSet<>();

   @OneToMany(cascade = CascadeType.ALL)
    Set<Guide> guides=new HashSet<>();


}
