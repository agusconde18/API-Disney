package com.alkemy.disney.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    //@ManyToMany(mappedBy = "films??",fetch = FetchType.LAZY)
    //FilmObject id;


}