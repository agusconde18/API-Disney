package com.alkemy.disney.dto.Characters;

import com.alkemy.disney.entity.Film;
import com.alkemy.disney.exception.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class CharactersDTO {

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

    @JsonIgnoreProperties("characters")
    Set<Film> actFilm = new HashSet<>();

}
