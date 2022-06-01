package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.exception.*;
import com.alkemy.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/films")
public class FilmController {

    FilmService filmService;

    @Autowired
    FilmController(FilmService filmService){
        this.filmService = filmService;
    }


    @PostMapping
    public ResponseEntity<?> newFilm(@Valid  @RequestBody FilmPostDTO film) throws ParseException {
            return new ResponseEntity<>(filmService.save(film), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FilmListDTO>> getAll() {
        return new ResponseEntity<>(filmService.getAllFilms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmDetails(@Valid @PathVariable @NotNull(message = ErrorMessages.NOT_NULL) Long id) throws NotFound {
            return new ResponseEntity<>(filmService.getFilmDetails(id), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @RequestBody FilmPostDTO film
    ) throws  NotFound, ParseException {
            return new ResponseEntity<>(filmService.update(film, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id
    ) throws NotFound {
            filmService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}/character/{characterId}")
    public ResponseEntity<?> updateCharacterList(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long characterId
    ) throws NotFound {
            return new ResponseEntity<>(filmService.updateCharacters(id, characterId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/character/{characterId}")
    public void deleteCharacterFromList(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long characterId
    ) throws NotFound {
            filmService.deleteCharacter(id, characterId);
    }
    @PostMapping("/{id}/character")
    public ResponseEntity<?> newCharacterForFilm(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @RequestBody PostCharactersDTO newChar
    ) throws DatabaseError {

            return new ResponseEntity<>(filmService.updateNewCharacters(id, newChar), HttpStatus.OK);

    }
}
