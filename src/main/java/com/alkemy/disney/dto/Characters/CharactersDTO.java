package com.alkemy.disney.dto.Characters;

import com.alkemy.disney.entity.Film;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class CharactersDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @Min(0)
    @Max(150)
    private Long age;

    @NotNull
    private String image;

    @Min(0)
    @Max(1000)
    private Float weight;

    @NotNull
    private String story;

    @JsonIgnoreProperties("characters")
    Set<Film> actFilm = new HashSet<>();
}
