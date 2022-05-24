package com.alkemy.disney.dto.Characters;

import com.alkemy.disney.entity.Film;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class CharactersDTO {

    private Long id;

    private String name;

    private Long age;

    private String image;

    private Float weight;

    private String story;

    Set<Film> actFilm = new HashSet<>();
}
