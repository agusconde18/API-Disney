package com.alkemy.disney.service;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;

import java.util.List;

public interface FilmService {
    FilmDTO save(FilmPostDTO film) throws ServiceError;
    void delete(Long id) throws DatabaseError;
    FilmDTO update(FilmPostDTO film, Long id) throws ServiceError, DatabaseError;
    FilmDTO updateCharacters(Long id, Long idCharacter) throws DatabaseError;
    FilmDTO updateNewCharacters(Long id, PostCharactersDTO newChar) throws DatabaseError;
    void deleteCharacter(Long id, Long idCharacter) throws DatabaseError;
    FilmDTO getFilmDetails(Long id) throws DatabaseError;
    List<FilmListDTO> getAllFilms();
}
