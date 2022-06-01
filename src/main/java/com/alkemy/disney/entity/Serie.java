package com.alkemy.disney.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "series")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private Double rating;
    private Date releaseDate;
    private String coverImage;
    private Integer totalEpisodes;

    private boolean deleted = false;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("actFilm")
    Set<CharacterDat> characters = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;
}
