package com.alkemy.disney.dto.Films;

import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class FilmPostDTO {
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    @Min(0)
    @Max(10)
    private Double rating;

    @NotEmpty
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private String date;

    private Date releaseDate;

    @NotEmpty
    @NotNull
    private String coverImage;

    @NotNull
    Set<CharacterDat> characters = new HashSet<>();

    @NotNull
    private Genre genre;
}
