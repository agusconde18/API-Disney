package com.alkemy.disney.dto.Films;

import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.ErrorMessages;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmPostDTO {
    private Long id;

    @NotNull (message = ErrorMessages.NOT_NULL)
    @NotEmpty (message = ErrorMessages.NOT_EMPTY)
    private String title;

    @Min(value = 0 , message = ErrorMessages.MIN_VALUE_ERROR)
    @Max(value = 10 ,message = ErrorMessages.MAX_VALUE_ERROR + 10)
    private Double rating;

    @NotNull (message = ErrorMessages.NOT_NULL)
    @NotEmpty (message = ErrorMessages.NOT_EMPTY)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private String date;

    private Date releaseDate;

    @NotNull (message = ErrorMessages.NOT_NULL)
    @NotEmpty (message = ErrorMessages.NOT_EMPTY)
    private String coverImage;

    @NotNull (message = ErrorMessages.NOT_NULL)
    Set<CharacterDat> characters = new HashSet<>();

    @NotNull (message = ErrorMessages.NOT_NULL)
    private Genre genre;
}
