package com.alkemy.disney.dto.Films;

import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FilmPostDTO {
    private Long id;

    private String title;
    private Double rating;
    private String date;
    private Date releaseDate;
    private String coverImage;

    @JsonIgnoreProperties("actFilm")
    Set<CharacterDat> characters = new HashSet<>();

    private Genre genre;
}
