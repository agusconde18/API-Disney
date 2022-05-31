package com.alkemy.disney.service;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Series.SerieDTO;
import com.alkemy.disney.dto.Series.SerieListDTO;
import com.alkemy.disney.dto.Series.SeriePostDTO;
import com.alkemy.disney.entity.Serie;

public interface SerieService {
    SerieDTO save(SeriePostDTO serie);
    void delete(Long id);
    SerieDTO update(Long id, SeriePostDTO serie);
    SerieDTO updateCharacters(Long id, Long characterId);
    SerieDto updateNewCharacter(Long id, PostCharactersDTO newChar);
    SerieDTO deleteCharacter(Long id, Long characterId);
    SerieDTO getSerieDetails(Long id);
    List<SerieListDTO> getAllSeries();
}
