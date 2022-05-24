package com.alkemy.disney.service.impl;

import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.repository.CharacterDatRepository;
import com.alkemy.disney.repository.FilmRepository;
import com.alkemy.disney.service.FilmService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.text.SimpleDateFormat;

@NoArgsConstructor
@Service
public class FilmServiceImp implements FilmService {

    FilmRepository filmRepository;
    CharacterDatRepository characterDatRepository;

    @Autowired
    public FilmServiceImp(FilmRepository filmRepository, CharacterDatRepository characterDatRepository){
        this.filmRepository = filmRepository;
        this.characterDatRepository = characterDatRepository;
    }

    @Override
    public Film save(Film film) throws ServiceError {
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
        try {
            film.setReleaseDate(formatter.parse(film.getDate()));
        } catch(ParseException e) {

        }
        if(!film.getTitle().isEmpty() && !film.getCoverImage().isEmpty() && film.getReleaseDate() != null) {
            return filmRepository.save(film);
        } else {
            throw new ServiceError("Los campos son obligatorios");
        }

    }

    @Override
    public void delete(Long id) throws DatabaseError{
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToDelete = res.get();
            filmRepository.delete(filmToDelete);
        } else {
        }
        throw new DatabaseError("No se pudo encontrar una pelicula con ese id");
    }

    @Override
    public Film update(Film film, Long id) throws ServiceError, DatabaseError{
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToUpdate = res.get();
            if(!film.getTitle().isEmpty() && !film.getCoverImage().isEmpty() && !film.getDate().isEmpty()) {
                film.setId(filmToUpdate.getId());
                SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
                try {
                    film.setReleaseDate(formatter.parse(film.getDate()));
                } catch(ParseException e) {

                }
                return filmRepository.save(film);
            } else {
                throw new ServiceError("Los campos son obligatorios");
            }
        } else {
            throw new DatabaseError("No se pudo encontrar una pelicula con ese id");
        }

    }

    @Override
    public void updateCharacters(Long id, Long idCharacter) throws DatabaseError{
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToUpdate = res.get();
            Optional<CharacterDat> charRes = characterDatRepository.findById(idCharacter);
            if (charRes.isPresent()) {
                CharacterDat character = charRes.get();
                Set<CharacterDat> updatedCharacters = filmToUpdate.getCharacters();
                updatedCharacters.add(character);
                filmToUpdate.setCharacters(updatedCharacters);
                filmRepository.save(filmToUpdate);
            } else {
                throw new DatabaseError("No se pudo encontrar un personaje con ese id");
            }
            } else {
            throw new DatabaseError("No se pudo encontrar una pelicula con ese id");
        }
    }

    @Override
    public Film getFilmDetails(Long id) throws DatabaseError{
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmDetails = res.get();
            return filmDetails;
        } else {
            throw new DatabaseError("No se pudo encontrar una pelicula con ese id");
        }
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }
}
