package com.alkemy.disney.service;

import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import com.alkemy.disney.entity.Film;

import java.util.List;

public interface FilmService {
    Film save(Film film) throws ServiceError;
    void delete(Long id) throws DatabaseError;
    Film update(Film film, Long id) throws ServiceError, DatabaseError;
    void updateCharacters(Long id, Long idCharacter) throws DatabaseError;
    Film getFilmDetails(Long id) throws DatabaseError;
    List<Film> getAllFilms();
}
