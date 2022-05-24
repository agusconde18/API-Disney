package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Film;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import com.alkemy.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movie")
public class FilmController {

    FilmService filmService;

    @Autowired
    FilmController(FilmService filmService){
        this.filmService = filmService;
    }


    @PostMapping("/")
    public ResponseEntity<?> newFilm(@RequestBody Film film) {
        try {
            return new ResponseEntity<>(filmService.save(film), HttpStatus.CREATED);
        }catch (ServiceError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Film>> getAll() {
        return new ResponseEntity<>(filmService.getAllFilms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmDetails(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(filmService.getFilmDetails(id), HttpStatus.OK);
        } catch (DatabaseError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        try {
            return new ResponseEntity<>(filmService.update(film, id), HttpStatus.CREATED);
        }catch (ServiceError|DatabaseError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Long id) {
        try {
            filmService.delete(id);
        }catch (DatabaseError e) {
            
        }
    }
}
