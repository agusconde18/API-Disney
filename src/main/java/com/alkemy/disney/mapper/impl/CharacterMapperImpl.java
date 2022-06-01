package com.alkemy.disney.mapper.impl;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.CharactersDTOSet;
import com.alkemy.disney.dto.Characters.ListCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.mapper.CharacterMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CharacterMapperImpl implements CharacterMapper {

    @Override
    public CharactersDTO CharactersToDTO(CharacterDat category) {
        if ( category == null ) {
            return null;
        }

        CharactersDTO charactersDTO = new CharactersDTO();

        charactersDTO.setId( category.getId() );
        charactersDTO.setName( category.getName() );
        charactersDTO.setAge( category.getAge() );
        charactersDTO.setImage( category.getImage() );
        charactersDTO.setWeight( category.getWeight() );
        charactersDTO.setStory( category.getStory() );
        charactersDTO.setActFilm( filmSetToFilmDTOSet( category.getActFilm() ) );

        return charactersDTO;
    }

    @Override
    public CharacterDat DTOToCharacter(CharactersDTO characterDTO) {
        if ( characterDTO == null ) {
            return null;
        }

        CharacterDat characterDat = new CharacterDat();

        characterDat.setId( characterDTO.getId() );
        characterDat.setName( characterDTO.getName() );
        characterDat.setAge( characterDTO.getAge() );
        characterDat.setImage( characterDTO.getImage() );
        characterDat.setWeight( characterDTO.getWeight() );
        characterDat.setStory( characterDTO.getStory() );
        characterDat.setActFilm( filmDTOSetToFilmSet( characterDTO.getActFilm() ) );

        return characterDat;
    }

    @Override
    public CharacterDat PostCharactersDToCharacterDat(PostCharactersDTO characterPostDTO) {
        if ( characterPostDTO == null ) {
            return null;
        }

        CharacterDat characterDat = new CharacterDat();

        characterDat.setId( characterPostDTO.getId() );
        characterDat.setName( characterPostDTO.getName() );
        characterDat.setAge( characterPostDTO.getAge() );
        characterDat.setImage( characterPostDTO.getImage() );
        characterDat.setWeight( characterPostDTO.getWeight() );
        characterDat.setStory( characterPostDTO.getStory() );
        characterDat.setActFilm( filmDTOSetToFilmSet( characterPostDTO.getActFilm() ) );

        return characterDat;
    }

    @Override
    public ListCharactersDTO CharactersToDTOList(CharacterDat category) {
        if ( category == null ) {
            return null;
        }

        ListCharactersDTO listCharactersDTO = new ListCharactersDTO();

        listCharactersDTO.setId( category.getId() );
        listCharactersDTO.setName( category.getName() );
        listCharactersDTO.setImage( category.getImage() );

        return listCharactersDTO;
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

    protected FilmDTO filmToFilmDTO(Film film) {
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

    protected Set<FilmDTO> filmSetToFilmDTOSet(Set<Film> set) {
        if ( set == null ) {
            return null;
        }

        Set<FilmDTO> set1 = new HashSet<FilmDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Film film : set ) {
            set1.add( filmToFilmDTO( film ) );
        }

        return set1;
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

    protected Film filmDTOToFilm(FilmDTO filmDTO) {
        if ( filmDTO == null ) {
            return null;
        }

        Film film = new Film();

        film.setId( filmDTO.getId() );
        film.setTitle( filmDTO.getTitle() );
        film.setRating( filmDTO.getRating() );
        film.setReleaseDate( filmDTO.getReleaseDate() );
        film.setCoverImage( filmDTO.getCoverImage() );
        film.setCharacters( charactersDTOSetSetToCharacterDatSet( filmDTO.getCharacters() ) );
        film.setGenre( genreDTOToGenre( filmDTO.getGenre() ) );

        return film;
    }

    protected Set<Film> filmDTOSetToFilmSet(Set<FilmDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Film> set1 = new HashSet<Film>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( FilmDTO filmDTO : set ) {
            set1.add( filmDTOToFilm( filmDTO ) );
        }

        return set1;
    }

}
