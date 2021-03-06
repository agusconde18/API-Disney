package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.Series.SerieDTO;
import com.alkemy.disney.dto.Series.SerieListDTO;
import com.alkemy.disney.dto.Series.SeriePostDTO;
import com.alkemy.disney.entity.Serie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;

//@Mapper(componentModel = "spring")
public interface SerieMapper {
    SerieMapper INSTANCE = Mappers.getMapper(SerieMapper.class);

    SerieDTO seriesToDTO(Serie serie);
    Serie PostSerieDTOToSerie(SeriePostDTO seriePostDTO) throws ParseException;
    SerieListDTO seriesToDTOList(Serie serie);
}
