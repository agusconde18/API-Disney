package com.alkemy.disney.dto.Characters;

import com.alkemy.disney.entity.Film;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class PostCharactersDTO {
    private Long id;

    private String name;

    private Long age;

    private String image;

    private Float weight;

    private String story;

    Set<Film> actFilm = new HashSet<>();
}