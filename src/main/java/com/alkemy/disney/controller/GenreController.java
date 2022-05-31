package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Genres.GenreDTO;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private Map <String,Object> makeMap(String key, Object value){
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    @GetMapping()
    List<GenreDTO> getAllGenres(){
        return genreService.getAll();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createGenre(@RequestBody GenreDTO genreDTO){
        GenreDTO response = genreService.save(genreDTO);
        return new ResponseEntity<>(makeMap("saved", response), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>>updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO){
        GenreDTO response = genreService.update(genreDTO, id);
        return new ResponseEntity<>(makeMap("updated", response), HttpStatus.CREATED);
    }
}
