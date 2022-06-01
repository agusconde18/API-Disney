package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.exception.NotFound;

import com.alkemy.disney.exception.NotValid;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/genre")
public class GenreController {

    GenreService genreService;

    @Autowired
    GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping
    List<GenreDTO> getAllGenres(){
        return genreService.getAll();
    }

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO) throws NotValid {
        GenreDTO response = genreService.save(genreDTO);
        return new ResponseEntity<GenreDTO>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO>updateGenre(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody GenreDTO genreDTO) throws NotFound {
        GenreDTO response = genreService.update(genreDTO, id);
        return new ResponseEntity<GenreDTO>(response, HttpStatus.OK);
    }
}
