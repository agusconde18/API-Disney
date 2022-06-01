package com.alkemy.disney.mapper.impl;

import com.alkemy.disney.dto.Series.SerieDTO;
import com.alkemy.disney.dto.Series.SerieListDTO;
import com.alkemy.disney.dto.Series.SeriePostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Serie;
import com.alkemy.disney.mapper.SerieMapper;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashSet;
import java.util.Set;

@Qualifier
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
        Set<CharacterDat> set = serie.getCharacters();
        if ( set != null ) {
            serieDTO.setCharacters( new HashSet<CharacterDat>( set ) );
        }
        serieDTO.setGenre( serie.getGenre() );

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
        Set<CharacterDat> set = seriePostDTO.getCharacters();
        if ( set != null ) {
            serie.setCharacters( new HashSet<CharacterDat>( set ) );
        }
        serie.setGenre( seriePostDTO.getGenre() );

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

}
