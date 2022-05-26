package com.alkemy.disney.dto.Films;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FilmListDTO {
    private Long id;

    private String title;

    private Date releaseDate;
    private String coverImage;
}
