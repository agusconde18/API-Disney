package com.alkemy.disney.service;

import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;

import java.util.List;

public interface FilmService {
    FilmDTO save(FilmPostDTO film) throws ServiceError;
    void delete(Long id) throws DatabaseError;
    FilmDTO update(FilmPostDTO film, Long id) throws ServiceError, DatabaseError;
    void updateCharacters(Long id, Long idCharacter) throws DatabaseError;
    FilmDTO getFilmDetails(Long id) throws DatabaseError;
    List<FilmListDTO> getAllFilms();
}
