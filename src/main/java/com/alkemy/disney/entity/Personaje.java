package com.alkemy.disney.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personaje")
@Getter
@Setter
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Long age;

    private String image;

    private Float weight;

    private String history;

    @ManyToMany(mappedBy = "characters",fetch = FetchType.LAZY)
    Set<Film> actFilm = new HashSet<>();


}