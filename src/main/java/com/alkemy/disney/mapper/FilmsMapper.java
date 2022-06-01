package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.Film;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public interface FilmsMapper {

    FilmDTO filmsToDTO(Film film);

    Film PostFilmDTOToFilm(FilmPostDTO filmPostDTO) throws ParseException;

    FilmListDTO filmsToDTOList (Film film);
}
