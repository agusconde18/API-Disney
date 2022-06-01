package com.alkemy.disney.dto.Series;

import com.alkemy.disney.dto.Characters.CharactersDTOSet;
import com.alkemy.disney.dto.Genres.GenreDTO;
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
public class SerieDTO {
    private Long id;

    private String title;
    private Double rating;
    private Date releaseDate;
    private String coverImage;
    private Integer totalEpisodes;

    @JsonIgnoreProperties("actFilm")
    Set<CharactersDTOSet> characters = new HashSet<>();

    @JsonIgnoreProperties("hibernateLazyInitializer")
    private GenreDTO genre;
}
