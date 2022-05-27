package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.service.CharactersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/characters")
public class CharactersController {

    @Autowired
    CharactersServiceInterface charactersService;


    @GetMapping

    ResponseEntity<?> getAllCharacters (){
        return new ResponseEntity<>(charactersService.allCharacters(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById (@Valid @PathVariable @NotNull Long id){
        try {
            return new ResponseEntity<>(charactersService.getById(id), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> newCharacter (@Valid @RequestBody PostCharactersDTO postCharactersDTO) {
        try {
            return new ResponseEntity<>(charactersService.newCharacter(postCharactersDTO), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCharacter (@Valid @PathVariable @NotNull Long id) {
        try {
            charactersService.deleteCharacter(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    ResponseEntity<?> editCharacter (
            @Valid @PathVariable @NotNull Long id,
            @Valid @RequestBody PostCharactersDTO postCharactersDTO
    ){
        try {
            postCharactersDTO.setId(id);
            return new ResponseEntity<>(charactersService.editCharacter(postCharactersDTO), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
