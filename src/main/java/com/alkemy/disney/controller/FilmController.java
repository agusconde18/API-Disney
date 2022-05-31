package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Films.FilmDTO;
import com.alkemy.disney.dto.Films.FilmListDTO;
import com.alkemy.disney.dto.Films.FilmPostDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import com.alkemy.disney.service.FilmService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public ResponseEntity<?> newFilm(@Valid  @RequestBody FilmPostDTO film) {
        try {
            return new ResponseEntity<>(filmService.save(film), HttpStatus.CREATED);
        }catch (ServiceError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<FilmListDTO>> getAll() {
        return new ResponseEntity<>(filmService.getAllFilms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmDetails(@Valid @PathVariable @NotNull Long id) {
        try {
            return new ResponseEntity<>(filmService.getFilmDetails(id), HttpStatus.OK);
        } catch (DatabaseError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(
            @Valid @PathVariable @NotNull Long id,
            @Valid @RequestBody FilmPostDTO film
    ) {
        try {
            return new ResponseEntity<>(filmService.update(film, id), HttpStatus.CREATED);
        }catch (ServiceError|DatabaseError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@Valid @PathVariable @NotNull Long id) {
        try {
            filmService.delete(id);
        }catch (DatabaseError e) {

        }
    }

    @PutMapping("/{id}/character/{charId}")
    public void updateCharacterList(
            @Valid @PathVariable @NotNull Long id,
            @Valid @PathVariable @NotNull Long charId
    ) {
        try {
            return new ResponseEntity<>(filmService.updateCharacters(id, charId), HttpStatus.OK);
        } catch (DatabaseError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/character/{charId}")
    public void deleteCharacterFromList(
            @Valid @PathVariable @NotNull Long id,
            @Valid @PathVariable @NotNull Long charId
    ){
        try {
            filmService.deleteCharacter(id, charId);
        } catch (DatabaseError e){

        }
    }
    @PostMapping("/{id}/character")
    public void newCharacterForFilm(
            @Valid @PathVariable @NotNull Long id,
            @Valid @RequestBody CharacterDat newChar
    ){
        try {
            return new ResponseEntity<>(filmService.updateNewCharacters(id, newChar), HttpStatus.OK);
        } catch (DatabaseError e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
