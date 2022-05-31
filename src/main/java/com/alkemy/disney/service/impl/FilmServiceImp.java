package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.exception.*;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.mapper.FilmsMapper;
import com.alkemy.disney.repository.CharacterDatRepository;
import com.alkemy.disney.repository.FilmRepository;
import com.alkemy.disney.repository.GenreRepository;
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

    GenreRepository genreRepository;
    FilmRepository filmRepository;
    CharacterDatRepository characterDatRepository;

    FilmsMapper filmsMapper = FilmsMapper.INSTANCE;
    CharacterMapper characterMapper = CharacterMapper.INSTANCE;

    @Autowired
    public FilmServiceImp(GenreRepository genreRepository, FilmRepository filmRepository, CharacterDatRepository characterDatRepository){
        this.genreRepository = genreRepository;
        this.characterDatRepository = characterDatRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmDTO save(FilmPostDTO film) throws DateFormatException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");

        try {
            film.setReleaseDate(formatter.parse(film.getDate()));
        } catch (ParseException e) {
            throw new DateFormatException(ErrorMessages.ERROR_DATE, e.getErrorOffset());
        }

        Film newFilm = filmsMapper.PostFilmDTOToFilm(film);

        newFilm.setGenre(genreRepository.getById(newFilm.getGenre().getId()));

        filmRepository.save(newFilm);
        return filmsMapper.filmsToDTO(newFilm);
    }

    @Override
    public void delete(Long id) throws NotFound {
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToDelete = res.get();
            filmRepository.delete(filmToDelete);
            return;
        }
        throw new NotFound(ErrorMessages.FILM_NOT_FOUND);
    }

    @Override
    public FilmDTO update(FilmPostDTO film, Long id) throws NotFound, NotValid {
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToUpdate = res.get();
            film.setId(filmToUpdate.getId());
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            try {
                film.setReleaseDate(formatter.parse(film.getDate()));
            } catch (ParseException e) {
                throw new NotValid(ErrorMessages.ERROR_DATE);
            }
            Film updateFilm = filmsMapper.PostFilmDTOToFilm(film);
            updateFilm.setCharacters(filmToUpdate.getCharacters());

            updateFilm.setGenre(genreRepository.getById(updateFilm.getGenre().getId()));

            filmRepository.save(updateFilm);
            return filmsMapper.filmsToDTO(updateFilm);
        }
        throw new NotFound(ErrorMessages.FILM_NOT_FOUND);
    }

    @Override
    public FilmDTO updateCharacters(Long id, Long idCharacter) throws NotFound {
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
                return filmsMapper.filmsToDTO(filmToUpdate);
            }
            throw new NotFound(ErrorMessages.CHARACTER_NOT_FOUND);
        }
        throw new NotFound(ErrorMessages.FILM_NOT_FOUND);
    }


    @Override
    public FilmDTO updateNewCharacters(Long id, PostCharactersDTO newChar) throws DatabaseError {
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToUpdate = res.get();
            CharacterDat charToSave = characterMapper.PostCharactersDToCharacterDat(newChar);
            characterDatRepository.save(charToSave);
            filmToUpdate.getCharacters().add(charToSave);
            filmRepository.save(filmToUpdate);
            return filmsMapper.filmsToDTO(filmToUpdate);
        }
        throw new DatabaseError(ErrorMessages.FILM_NOT_FOUND);
    }

    @Override
    public void deleteCharacter(Long id, Long idCharacter) throws NotFound {
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
                return;
            }
            else
                throw new NotFound(ErrorMessages.CHARACTER_NOT_FOUND);
        }
        throw new NotFound(ErrorMessages.FILM_NOT_FOUND);
    }

    @Override
    public FilmDTO getFilmDetails(Long id) throws NotFound {
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmDetails = res.get();
            return filmsMapper.filmsToDTO(filmDetails);
        }

        throw new NotFound(ErrorMessages.FILM_NOT_FOUND);
    }

    @Override
    public List<FilmListDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream().map( filmsMapper::filmsToDTOList )
                .collect(Collectors.toList());
    }
}
