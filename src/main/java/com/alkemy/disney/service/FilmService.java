package com.alkemy.disney.service;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.exception.NotValid;
import com.alkemy.disney.exception.ServiceError;

import java.text.ParseException;
import java.util.List;

public interface FilmService {
    FilmDTO save(FilmPostDTO film) throws  NotValid;
    void delete(Long id);
    FilmDTO update(FilmPostDTO film, Long id) throws  NotFound, NotValid;
    FilmDTO updateCharacters(Long id, Long idCharacter) throws NotFound;
    FilmDTO updateNewCharacters(Long id, PostCharactersDTO newChar) throws DatabaseError;
    void deleteCharacter(Long id, Long idCharacter) throws NotFound;
    FilmDTO getFilmDetails(Long id) throws DatabaseError;
    List<FilmListDTO> getAllFilms();
}
