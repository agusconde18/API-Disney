package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.dto.Series.SerieDTO;
import com.alkemy.disney.dto.Series.SerieListDTO;
import com.alkemy.disney.dto.Series.SeriePostDTO;
import com.alkemy.disney.entity.Serie;
import com.alkemy.disney.exception.*;
import com.alkemy.disney.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/series")
public class SerieController {

    SerieService serieService;

    @Autowired
    SerieController(SerieService serieService){
        this.serieService = serieService;
    }

    @PostMapping
    public ResponseEntity<SerieDTO> newSerie(@Valid @RequestBody SeriePostDTO serie) throws DateFormatException{
        return new ResponseEntity<>(serieService.save(serie), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<SerieListDTO>> getAll(){
        return new ResponseEntity<>(serieService.getAllSeries(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> getFilmDetails(@Valid @PathVariable @NotNull(message=ErrorMessages.NOT_NULL) Long id) throws NotFound{
        return new ResponseEntity<>(serieService.getSerieDetails(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SerieDTO> updateFilm(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @RequestBody SeriePostDTO serie
    ) throws NotFound, DateFormatException{
        return new ResponseEntity<>(serieService.update(id, serie), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilm(@Valid @PathVariable @NotNull(message=ErrorMessages.NOT_NULL) Long id){
        serieService.delete(id);
        return new ResponseEntity<>("Deleted Series with id: "+ id, HttpStatus.OK);
    }

    @PostMapping("/{id}/character")
    public ResponseEntity<SerieDTO> newCharacterForSerie(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @RequestBody PostCharactersDTO newChar
    ) throws NotFound{
        return new ResponseEntity<>(serieService.updateNewCharacter(id, newChar), HttpStatus.OK);
    }

    @PutMapping("/{id}/character/{characterId}")
    public ResponseEntity<SerieDTO> updateCharacterList(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long characterId
    ) throws NotFound{
        return new ResponseEntity<>(serieService.updateCharacters(id, characterId), HttpStatus.OK);
    }
    @DeleteMapping("/{id}/character/{characterId}")
    public ResponseEntity<SerieDTO> deleteCharacterFromList(
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long characterId
    ) throws NotFound{
        return new ResponseEntity<>(serieService.deleteCharacter(id, characterId), HttpStatus.OK);
    }
}
