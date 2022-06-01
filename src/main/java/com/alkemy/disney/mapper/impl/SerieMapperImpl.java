package com.alkemy.disney.mapper.impl;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.CharactersDTOSet;
import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.dto.Series.SerieDTO;
import com.alkemy.disney.dto.Series.SerieListDTO;
import com.alkemy.disney.dto.Series.SeriePostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.entity.Serie;
import com.alkemy.disney.mapper.SerieMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SerieMapperImpl implements SerieMapper {

    @Override
    public SerieDTO seriesToDTO(Serie serie) {
        if ( serie == null ) {
            return null;
        }

        SerieDTO serieDTO = new SerieDTO();

        serieDTO.setId( serie.getId() );
        serieDTO.setTitle( serie.getTitle() );
        serieDTO.setRating( serie.getRating() );
        serieDTO.setReleaseDate( serie.getReleaseDate() );
        serieDTO.setCoverImage( serie.getCoverImage() );
        serieDTO.setTotalEpisodes( serie.getTotalEpisodes() );
        Set<CharactersDTOSet> set = (characterDatSetToCharactersDTOSetSet(serie.getCharacters()));
        if ( set != null ) {
            serieDTO.setCharacters( new HashSet<CharactersDTOSet>( set ) );
        }
        serieDTO.setGenre( genreToGenreDTO(serie.getGenre()));

        return serieDTO;
    }

    @Override
    public Serie PostSerieDTOToSerie(SeriePostDTO seriePostDTO) {
        if ( seriePostDTO == null ) {
            return null;
        }

        Serie serie = new Serie();

        serie.setId( seriePostDTO.getId() );
        serie.setTitle( seriePostDTO.getTitle() );
        serie.setRating( seriePostDTO.getRating() );
        serie.setReleaseDate( seriePostDTO.getReleaseDate() );
        serie.setCoverImage( seriePostDTO.getCoverImage() );
        serie.setTotalEpisodes( seriePostDTO.getTotalEpisodes() );
        Set<CharacterDat> set = charactersDTOSetSetToCharacterDatSet(seriePostDTO.getCharacters());
        if ( set != null ) {
            serie.setCharacters( new HashSet<CharacterDat>( set ) );
        }
        serie.setGenre( genreDTOToGenre(seriePostDTO.getGenre()));

        return serie;
    }

    @Override
    public SerieListDTO seriesToDTOList(Serie serie) {
        if ( serie == null ) {
            return null;
        }

        SerieListDTO serieListDTO = new SerieListDTO();

        serieListDTO.setId( serie.getId() );
        serieListDTO.setTitle( serie.getTitle() );
        serieListDTO.setReleaseDate( serie.getReleaseDate() );
        serieListDTO.setCoverImage( serie.getCoverImage() );

        return serieListDTO;
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
