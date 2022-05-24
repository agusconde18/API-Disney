package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class GenreController {

    GenreService genreService;

    @Autowired
    GenreController(GenreService genreService){

        this.genreService = genreService;
    }

    private Map <String,Object> makeMap(String key, Object value){
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }


    @PostMapping("/genre")
    public ResponseEntity<Map<String, Object>> createGenre(@RequestBody Genre genre){
        try{
            genreService.save(genre);
            return new ResponseEntity<>(makeMap("id", genre.getId()), HttpStatus.CREATED);

        }catch(ServiceError e){
            return new ResponseEntity<>(makeMap("error", e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/genre/{id}")
    public ResponseEntity<Map<String, Object>>updateGenre(@PathVariable Long id, @RequestBody Genre genre){
        try{
            genreService.update(genre, id);
            return new ResponseEntity<>( makeMap("id", genre.getId()), HttpStatus.CREATED);
        }catch(ServiceError| DatabaseError e){
            return new ResponseEntity<>(makeMap("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
