package com.alkemy.disney.dto.Characters;

import com.alkemy.disney.entity.Film;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostCharactersDTO {
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

    Set<Film> actFilm = new HashSet<>();
}
