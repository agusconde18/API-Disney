package com.alkemy.disney.dto.Characters;


import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.exception.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostCharactersDTO {

    private Long id;

    @NotNull (message = ErrorMessages.NOT_NULL)
    @NotEmpty (message = ErrorMessages.NOT_EMPTY)
    private String name;

    @Min(value = 0 , message = ErrorMessages.MIN_VALUE_ERROR)
    @Max(value = 150,message = ErrorMessages.MAX_VALUE_ERROR+150)
    private Long age;

    @NotNull (message = ErrorMessages.NOT_NULL)
    private String image;

    @Min(value = 0 , message = ErrorMessages.MIN_VALUE_ERROR)
    @Max(value = 1000,message = ErrorMessages.MAX_VALUE_ERROR+1000)
    private Float weight;

    @NotNull (message = ErrorMessages.NOT_NULL)
    private String story;

    Set<FilmDTO> actFilm = new HashSet<>();

}
