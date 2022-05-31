package com.alkemy.disney.dto.Films;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmListDTO {
    private Long id;

    private String title;

    private Date releaseDate;
    private String coverImage;
}
