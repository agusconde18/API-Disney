package com.alkemy.disney.mapper.impl;

import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier
public class GenreMapperImpl implements GenreMapper {

    @Override
    public GenreDTO GenreToDTO(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDTO genreDTO = new GenreDTO();

        genreDTO.setId( genre.getId() );
        genreDTO.setName( genre.getName() );

        return genreDTO;
    }

    @Override
    public Genre DTOToGenre(GenreDTO genreDTO) {
        if ( genreDTO == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setId( genreDTO.getId() );
        genre.setName( genreDTO.getName() );

        return genre;
    }

}
