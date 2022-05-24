package com.alkemy.disney.service.impl;

import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GenreServiceImp implements GenreService {

    GenreRepository genreRepository;

    @Autowired
    public GenreServiceImp(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(Genre genre) throws ServiceError {

        if(!genre.getName().isEmpty()){
            return genreRepository.save(genre);
        }else{
            throw new ServiceError("Falta el nombre del genero");
        }
    }

    @Override
    public Genre update(Genre genre, Long id) throws ServiceError, DatabaseError {
        Optional<Genre> res = genreRepository.findById(id);
        if(res.isPresent()){
            Genre genreToUpdate = res.get();
            if(!genre.getName().isEmpty()){
                genre.setId(genreToUpdate.getId());
                return genreRepository.save(genre);

            }else{
                throw new ServiceError("El nombre es obligatorio");
            }
        }else{
            throw new DatabaseError("No se pudo encontrar un genero con ese id");
        }
    }
}
