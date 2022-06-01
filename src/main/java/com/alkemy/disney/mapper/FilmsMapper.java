package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
public interface FilmsMapper {
    FilmsMapper INSTANCE = Mappers.getMapper(FilmsMapper.class);

    FilmDTO FilmsToDTO(Film film);

    Film PostFilmDTOToFilm(FilmPostDTO filmPostDTO);

    FilmListDTO FilmsToDTOList(Film film);
}
