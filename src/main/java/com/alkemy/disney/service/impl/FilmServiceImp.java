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

    @Autowired
    FilmsMapper filmsMapper;

    @Autowired
    CharacterMapper characterMapper;

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
        return filmsMapper.FilmsToDTO(newFilm);
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
    public FilmDTO update(FilmPostDTO film, Long id) throws NotFound, NotValid, ParseException {
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToUpdate = res.get();
            film.setId(filmToUpdate.getId());
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");

            film.setReleaseDate(formatter.parse(film.getDate()));

            Film updateFilm = filmsMapper.PostFilmDTOToFilm(film);

            /*
            *   En caso que se envie un cambio de genero se envia por ID
            *   Por lo tanto DEBO buscar en la BD el nombre perteneciente a dicho genero
             */
            Long newGenreId = updateFilm.getGenre().getId();
            if(genreRepository.existsById(newGenreId)) {
                updateFilm.getGenre().setName(
                        genreRepository.getById(newGenreId).getName()
                );
            }
            else
                throw new NotFound(ErrorMessages.ID_GENERO_INDEXISTENT);

            filmRepository.save(updateFilm);
            return filmsMapper.FilmsToDTO(updateFilm);
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
                return filmsMapper.FilmsToDTO(filmToUpdate);
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
            return filmsMapper.FilmsToDTO(filmToUpdate);
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
            return filmsMapper.FilmsToDTO(filmDetails);
        }

        throw new NotFound(ErrorMessages.FILM_NOT_FOUND);
    }

    @Override
    public List<FilmListDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream().map( filmsMapper::FilmsToDTOList)
                .collect(Collectors.toList());
    }
}
