package com.alkemy.disney.dto.Series;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SerieListDTO {
    private Long id;

    private String title;
    private Date releaseDate;
    private String coverImage;
}
