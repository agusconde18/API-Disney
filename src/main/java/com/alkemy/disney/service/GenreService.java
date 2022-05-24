package com.alkemy.disney.service;

import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;

public interface GenreService {

    Genre save(Genre genre) throws ServiceError;

    Genre update(Genre genre, Long id) throws ServiceError, DatabaseError;

}
