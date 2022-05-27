package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.mapper.FilmsMapper;
import com.alkemy.disney.repository.CharacterDatRepository;
import com.alkemy.disney.repository.FilmRepository;
import com.alkemy.disney.service.CharactersServiceInterface;
import com.alkemy.disney.service.FilmService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class FilmServiceImp implements FilmService {

    FilmRepository filmRepository;
    CharacterDatRepository characterDatRepository;
    FilmsMapper filmsMapper = FilmsMapper.INSTANCE;

    @Autowired
    public FilmServiceImp(FilmRepository filmRepository, CharacterDatRepository characterDatRepository){
        this.filmRepository = filmRepository;
        this.characterDatRepository = characterDatRepository;
    }

    @Override
    public FilmDTO save(FilmPostDTO film) throws ServiceError {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        try {
            film.setReleaseDate(formatter.parse(film.getDate()));
        } catch(ParseException e) {
            throw new ServiceError("Formato de fecha erroneo");
        }
        if(!film.getTitle().isEmpty() && !film.getCoverImage().isEmpty() && film.getReleaseDate() != null) {
            Film newFilm = filmsMapper.PostFilmDTOToFilm(film);
            filmRepository.save(newFilm);
            return filmsMapper.filmsToDTO(newFilm);
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
            throw new DatabaseError("No se pudo encontrar una pelicula con ese id");
        }
    }

    @Override
    public FilmDTO update(FilmPostDTO film, Long id) throws ServiceError, DatabaseError{
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToUpdate = res.get();
            if(!film.getTitle().isEmpty() && !film.getCoverImage().isEmpty() && !film.getDate().isEmpty()) {
                film.setId(filmToUpdate.getId());
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                try {
                    film.setReleaseDate(formatter.parse(film.getDate()));
                } catch(ParseException e) {
                    throw new ServiceError("Formato de fecha erroneo");
                }
                Film updateFilm = filmsMapper.PostFilmDTOToFilm(film);
                updateFilm.setCharacters(filmToUpdate.getCharacters());
                filmRepository.save(updateFilm);
                return filmsMapper.filmsToDTO(updateFilm);
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
    public void updateNewCharacters(Long id, CharacterDat newChar) throws DatabaseError{
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
                Film filmToUpdate = res.get();
                characterDatRepository.save(newChar);
                filmToUpdate.getCharacters().add(newChar);
                filmRepository.save(filmToUpdate);
        } else {
            throw new DatabaseError("No se pudo encontrar una pelicula con ese id");
        }
    }

    @Override
    public void deleteCharacter(Long id, Long idCharacter) throws DatabaseError{
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToUpdate = res.get();
            Optional<CharacterDat> charRes = characterDatRepository.findById(idCharacter);
            if (charRes.isPresent()) {
                CharacterDat character = charRes.get();
                Set<CharacterDat> updatedCharacters = filmToUpdate.getCharacters();
                updatedCharacters.remove(character);
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
    public FilmDTO getFilmDetails(Long id) throws DatabaseError{
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmDetails = res.get();
            return filmsMapper.filmsToDTO(filmDetails);
        } else {
            throw new DatabaseError("No se pudo encontrar una pelicula con ese id");
        }
    }

    @Override
    public List<FilmListDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream().map( filmsMapper::filmsToDTOList )
                .collect(Collectors.toList());
    }
}
