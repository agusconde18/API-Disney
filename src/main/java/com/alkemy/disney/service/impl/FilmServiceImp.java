package com.alkemy.disney.service.impl;

import com.alkemy.disney.Exceptions.ServiceError;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.repository.FilmRepository;
import com.alkemy.disney.service.FilmService;
import com.alkemy.disney.service.ValidationService.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImp implements FilmService {

    FilmRepository filmRepository;
    Validation validation;

    @Autowired
    public FilmServiceImp(Validation validation, FilmRepository filmRepository){
        this.filmRepository = filmRepository;
        this.validation = validation;
    }

    @Override
    public void save(Film film) throws ServiceError {
        if(validation.validateString(film.getTitle()) && validation.validateString(film.getCoverImage())) {
            filmRepository.save(film);
        } else {
            throw new ServiceError("Los campos título e imagen son obligatorios");
        }

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Film film, Long id) throws ServiceError{

    }

    @Override
    public Film getFilmDetails(Long id) {
        return null;
    }

    @Override
    public List<Film> getAllFilms() {
        return null;
    }
}
