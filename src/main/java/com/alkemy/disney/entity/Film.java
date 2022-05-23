package com.alkemy.disney.entity;

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
    private Integer rating;
    private Date releaseDate;
    private String coverImage;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    Set<CharacterDat> characters = new HashSet<>();

    @JoinTable(
            name = "rel_film_character",
            joinColumns = @JoinColumn(name = "IDfilm", nullable = false),
            inverseJoinColumns = @JoinColumn(name="IDcharacter", nullable = false)
    )

    @ManyToOne(fetch = FetchType.LAZY)
    private Genre genre;
}