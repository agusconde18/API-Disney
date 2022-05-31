package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "series")
@Getter
@Setter
public class Serie {

    private Long id;

    private String title;
    private Double rating;

    private Date releaseDate;
    private String coverImage;

    private Integer totalEpisodes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    Set<CharacterDat> characters = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;
}
