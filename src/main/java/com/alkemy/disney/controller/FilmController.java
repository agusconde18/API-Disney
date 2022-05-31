package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.exception.NotValid;
import com.alkemy.disney.exception.ServiceError;
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
    public ResponseEntity<?> newFilm(@Valid  @RequestBody FilmPostDTO film) throws  NotValid {
            return new ResponseEntity<>(filmService.save(film), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FilmListDTO>> getAll() {
        return new ResponseEntity<>(filmService.getAllFilms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmDetails(@Valid @PathVariable @NotNull Long id) throws DatabaseError {
            return new ResponseEntity<>(filmService.getFilmDetails(id), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(
            @Valid @PathVariable @NotNull Long id,
            @Valid @RequestBody FilmPostDTO film
    ) throws  NotFound, NotValid {
            return new ResponseEntity<>(filmService.update(film, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@Valid @PathVariable @NotNull Long id) {
            filmService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}/character/{charId}")
    public ResponseEntity<?> updateCharacterList(
            @Valid @PathVariable @NotNull Long id,
            @Valid @PathVariable @NotNull Long charId
    ) throws NotFound {
            return new ResponseEntity<>(filmService.updateCharacters(id, charId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/character/{charId}")
    public void deleteCharacterFromList(
            @Valid @PathVariable @NotNull Long id,
            @Valid @PathVariable @NotNull Long charId
    ) throws NotFound {
            filmService.deleteCharacter(id, charId);
    }
    @PostMapping("/{id}/character")
    public ResponseEntity<?> newCharacterForFilm(
            @Valid @PathVariable @NotNull Long id,
            @Valid @RequestBody PostCharactersDTO newChar
    ) throws DatabaseError {

            return new ResponseEntity<>(filmService.updateNewCharacters(id, newChar), HttpStatus.OK);

    }
}
