package com.alkemy.disney.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter

public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private Double rating;

    private Date releaseDate;
    private String coverImage;

    @JsonIgnoreProperties("actFilm")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    Set<CharacterDat> characters = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;


}