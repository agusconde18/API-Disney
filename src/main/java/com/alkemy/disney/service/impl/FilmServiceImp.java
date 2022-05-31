package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.exception.ServiceError;
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

    @Autowired
    GenreRepository genreRepository;

    FilmRepository filmRepository;
    CharacterDatRepository characterDatRepository;
    FilmsMapper filmsMapper = FilmsMapper.INSTANCE;
    CharacterMapper characterMapper = CharacterMapper.INSTANCE;

    @Autowired
    public FilmServiceImp(FilmRepository filmRepository, CharacterDatRepository characterDatRepository){
        this.filmRepository = filmRepository;
        this.characterDatRepository = characterDatRepository;
    }

    @Override
    public FilmDTO save(FilmPostDTO film) throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        film.setReleaseDate(formatter.parse(film.getDate()));

        Film newFilm = filmsMapper.PostFilmDTOToFilm(film);

        newFilm.setGenre(genreRepository.getById(newFilm.getGenre().getId()));

        filmRepository.save(newFilm);
        return filmsMapper.filmsToDTO(newFilm);
    }

    @Override
    public void delete(Long id){
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToDelete = res.get();
            filmRepository.delete(filmToDelete);
        }
    }

    @Override
    public FilmDTO update(FilmPostDTO film, Long id) throws ParseException, NotFound {
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmToUpdate = res.get();
            film.setId(filmToUpdate.getId());
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            film.setReleaseDate(formatter.parse(film.getDate()));
            Film updateFilm = filmsMapper.PostFilmDTOToFilm(film);
            updateFilm.setCharacters(filmToUpdate.getCharacters());

            updateFilm.setGenre(genreRepository.getById(updateFilm.getGenre().getId()));

            filmRepository.save(updateFilm);
            return filmsMapper.filmsToDTO(updateFilm);
        }
        throw new NotFound("");
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
            throw new NotFound("");
        }
        throw new NotFound("");
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
        throw new DatabaseError("No existe dicha pelicula");
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
            }
        }
        throw new NotFound("");
    }

    @Override
    public FilmDTO getFilmDetails(Long id) throws DatabaseError {
        Optional<Film> res = filmRepository.findById(id);
        if (res.isPresent()) {
            Film filmDetails = res.get();
            return filmsMapper.filmsToDTO(filmDetails);
        }

        throw new DatabaseError("");
    }

    @Override
    public List<FilmListDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream().map( filmsMapper::filmsToDTOList )
                .collect(Collectors.toList());
    }
}
