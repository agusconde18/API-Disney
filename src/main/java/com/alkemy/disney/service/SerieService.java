package com.alkemy.disney.service;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Series.SerieDTO;
import com.alkemy.disney.dto.Series.SerieListDTO;
import com.alkemy.disney.dto.Series.SeriePostDTO;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.exception.NotValid;

import java.text.ParseException;
import java.util.List;

public interface SerieService {
    SerieDTO save(SeriePostDTO serie) throws ParseException;
    void delete(Long id);
    SerieDTO update(Long id, SeriePostDTO serie) throws NotFound, ParseException;
    SerieDTO updateCharacters(Long id, Long characterId) throws NotFound;
    SerieDTO updateNewCharacter(Long id, PostCharactersDTO newChar) throws NotFound;
    SerieDTO deleteCharacter(Long id, Long characterId) throws NotFound;
    SerieDTO getSerieDetails(Long id) throws NotFound;
    List<SerieListDTO> getAllSeries();
}
