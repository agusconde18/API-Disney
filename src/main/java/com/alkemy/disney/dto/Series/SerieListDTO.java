package com.alkemy.disney.dto.Series;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SerieListDTO {
    private Long id;

    private String title;
    private Date releaseDate;
    private String coverImage;
}
