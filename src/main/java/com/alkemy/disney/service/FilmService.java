package com.alkemy.disney.service;

import com.alkemy.disney.Exceptions.ServiceError;
import com.alkemy.disney.entity.Film;

import java.util.List;

public interface FilmService {
    void save(Film film) throws ServiceError;
    void delete(Long id);
    void update(Film film, Long id) throws ServiceError;
    Film getFilmDetails(Long id);
    List<Film> getAllFilms();
}
