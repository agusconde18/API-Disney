package com.alkemy.disney.service;

import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.exception.ServiceError;

import java.util.List;

public interface GenreService {

    GenreDTO save(GenreDTO genreDTO) throws ServiceError;

    GenreDTO update(GenreDTO genreDTO, Long id) throws NotFound;

    List<GenreDTO> getAll();

}
