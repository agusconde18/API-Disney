package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDTO GenreToDTO(Genre genre);

    Genre DTOToGenre (GenreDTO genreDTO);
}
