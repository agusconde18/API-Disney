package com.alkemy.disney.dto.Films;

import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmDTO {
    private Long id;

    private String title;
    private Double rating;
    private Date releaseDate;
    private String coverImage;

    @JsonIgnoreProperties("actFilm")
    Set<CharacterDat> characters = new HashSet<>();

    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Genre genre;
}
