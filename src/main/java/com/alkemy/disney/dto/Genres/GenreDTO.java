package com.alkemy.disney.dto.Genres;

import com.alkemy.disney.exception.ErrorMessages;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class GenreDTO {
    private Long id;

    @NotNull(message = ErrorMessages.NOT_NULL)
    @NotEmpty(message = ErrorMessages.NOT_EMPTY)
    private String name;
}
