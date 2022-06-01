package com.alkemy.disney.mapper.impl;

import com.alkemy.disney.dto.Characters.CharactersDTOSet;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.ErrorMessages;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.mapper.FilmsMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Component
public class FilmsMapperImpl implements FilmsMapper {

    @Override
    public FilmDTO FilmsToDTO(Film film) {
        if ( film == null ) {
            return null;
        }

        FilmDTO filmDTO = new FilmDTO();

        filmDTO.setId( film.getId() );
        filmDTO.setTitle( film.getTitle() );
        filmDTO.setRating( film.getRating() );
        filmDTO.setReleaseDate( film.getReleaseDate() );
        filmDTO.setCoverImage( film.getCoverImage() );
        filmDTO.setCharacters( characterDatSetToCharactersDTOSetSet( film.getCharacters() ) );
        filmDTO.setGenre( genreToGenreDTO( film.getGenre() ) );

        return filmDTO;
    }

    @Override
    public Film PostFilmDTOToFilm(FilmPostDTO filmPostDTO) throws ParseException {
        if ( filmPostDTO == null ) {
            return null;
        }

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        filmPostDTO.setReleaseDate(formatter.parse(filmPostDTO.getDate()));

        Film film = new Film();

        film.setId( filmPostDTO.getId() );
        film.setTitle( filmPostDTO.getTitle() );
        film.setRating( filmPostDTO.getRating() );
        film.setReleaseDate( filmPostDTO.getReleaseDate() );
        film.setCoverImage( filmPostDTO.getCoverImage() );
        film.setCharacters( charactersDTOSetSetToCharacterDatSet( filmPostDTO.getCharacters() ) );
        film.setGenre( genreDTOToGenre( filmPostDTO.getGenre() ) );

        return film;
    }

    @Override
    public FilmListDTO FilmsToDTOList(Film film) {
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

    @Override
    public Film refreshValues(Film film, FilmPostDTO filmPostDTO) throws ParseException{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");

        film.setTitle(filmPostDTO.getTitle());
        film.setReleaseDate(formatter.parse(filmPostDTO.getDate()));
        film.setCoverImage(filmPostDTO.getCoverImage());
        film.setRating(filmPostDTO.getRating());

        return film;
    }

    protected CharactersDTOSet characterDatToCharactersDTOSet(CharacterDat characterDat) {
        if ( characterDat == null ) {
            return null;
        }

        CharactersDTOSet charactersDTOSet = new CharactersDTOSet();

        charactersDTOSet.setId( characterDat.getId() );
        charactersDTOSet.setName( characterDat.getName() );
        charactersDTOSet.setAge( characterDat.getAge() );
        charactersDTOSet.setImage( characterDat.getImage() );
        charactersDTOSet.setWeight( characterDat.getWeight() );
        charactersDTOSet.setStory( characterDat.getStory() );

        return charactersDTOSet;
    }

    protected Set<CharactersDTOSet> characterDatSetToCharactersDTOSetSet(Set<CharacterDat> set) {
        if ( set == null ) {
            return null;
        }

        Set<CharactersDTOSet> set1 = new HashSet<CharactersDTOSet>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CharacterDat characterDat : set ) {
            set1.add( characterDatToCharactersDTOSet( characterDat ) );
        }

        return set1;
    }

    protected GenreDTO genreToGenreDTO(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDTO genreDTO = new GenreDTO();

        genreDTO.setId( genre.getId() );
        genreDTO.setName( genre.getName() );

        return genreDTO;
    }

    protected CharacterDat charactersDTOSetToCharacterDat(CharactersDTOSet charactersDTOSet) {
        if ( charactersDTOSet == null ) {
            return null;
        }

        CharacterDat characterDat = new CharacterDat();

        characterDat.setId( charactersDTOSet.getId() );
        characterDat.setName( charactersDTOSet.getName() );
        characterDat.setAge( charactersDTOSet.getAge() );
        characterDat.setImage( charactersDTOSet.getImage() );
        characterDat.setWeight( charactersDTOSet.getWeight() );
        characterDat.setStory( charactersDTOSet.getStory() );

        return characterDat;
    }

    protected Set<CharacterDat> charactersDTOSetSetToCharacterDatSet(Set<CharactersDTOSet> set) {
        if ( set == null ) {
            return null;
        }

        Set<CharacterDat> set1 = new HashSet<CharacterDat>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CharactersDTOSet charactersDTOSet : set ) {
            set1.add( charactersDTOSetToCharacterDat( charactersDTOSet ) );
        }

        return set1;
    }

    protected Genre genreDTOToGenre(GenreDTO genreDTO) {
        if ( genreDTO == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setId( genreDTO.getId() );
        genre.setName( genreDTO.getName() );

        return genre;
    }

}
