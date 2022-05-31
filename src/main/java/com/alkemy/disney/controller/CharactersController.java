package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.NotFound;
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
    ResponseEntity<?> getById (@Valid @PathVariable @NotNull Long id) throws NotFound {
        return new ResponseEntity<>(charactersService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> newCharacter (@Valid @RequestBody PostCharactersDTO postCharactersDTO) {
        return new ResponseEntity<>(charactersService.newCharacter(postCharactersDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCharacter (@Valid @PathVariable @NotNull Long id) throws NotFound {
<<<<<<< HEAD
            charactersService.deleteCharacter(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
=======
        charactersService.deleteCharacter(id);
        return new ResponseEntity<>("", HttpStatus.OK);
>>>>>>> 9f2f4dfa5faab01a76011c2303c3c07e710a28cc
    }

    @PutMapping("/{id}")
    ResponseEntity<?> editCharacter (
            @Valid @PathVariable @NotNull Long id,
            @Valid @RequestBody PostCharactersDTO postCharactersDTO
    ) throws DatabaseError, NotFound {
        postCharactersDTO.setId(id);
        return new ResponseEntity<>(charactersService.editCharacter(postCharactersDTO), HttpStatus.ACCEPTED);
    }


}
