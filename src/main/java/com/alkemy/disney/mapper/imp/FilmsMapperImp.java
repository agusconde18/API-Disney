package com.alkemy.disney.mapper.imp;

import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Film;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import com.alkemy.disney.mapper.FilmsMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FilmsMapperImp {


    public FilmDTO filmsToDTO(Film film) {
        if ( film == null ) {
            return null;
        }

        FilmDTO filmDTO = new FilmDTO();

        filmDTO.setId( film.getId() );
        filmDTO.setTitle( film.getTitle() );
        filmDTO.setRating( film.getRating() );
        filmDTO.setReleaseDate( film.getReleaseDate() );
        filmDTO.setCoverImage( film.getCoverImage() );
        Set<CharacterDat> set = film.getCharacters();
        if ( set != null ) {
            filmDTO.setCharacters( new HashSet<CharacterDat>( set ) );
        }
        filmDTO.setGenre( film.getGenre() );

        return filmDTO;
    }


    public Film PostFilmDTOToFilm(FilmPostDTO filmPostDTO) throws ParseException {
        if ( filmPostDTO == null ) {
            return null;
        }

        System.out.println("HOLOOOOOO");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        filmPostDTO.setReleaseDate(formatter.parse(filmPostDTO.getDate()));

        Film film = new Film();

        film.setId( filmPostDTO.getId() );
        film.setTitle( filmPostDTO.getTitle() );
        film.setRating( filmPostDTO.getRating() );
        film.setReleaseDate( formatter.parse(filmPostDTO.getDate()) );
        film.setCoverImage( filmPostDTO.getCoverImage() );
        Set<CharacterDat> set = filmPostDTO.getCharacters();
        if ( set != null ) {
            film.setCharacters( new HashSet<CharacterDat>( set ) );
        }
        film.setGenre( filmPostDTO.getGenre() );

        return film;
    }


    public FilmListDTO filmsToDTOList(Film film) {
        if ( film == null ) {
            return null;
        }

        FilmListDTO filmListDTO = new FilmListDTO();

        filmListDTO.setId( film.getId() );
        filmListDTO.setTitle( film.getTitle() );
        filmListDTO.setReleaseDate( film.getReleaseDate() );
        filmListDTO.setCoverImage( film.getCoverImage() );

        return filmListDTO;
    }
}
