package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personaje")
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE personaje set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CharacterDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Long age;

    private String image;

    private Float weight;

    private boolean deleted = false;

    @Size(max=10000)
    private String story;

    @ManyToMany(mappedBy = "characters",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    Set<Film> actFilm = new HashSet<>();


}