package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.ListCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ErrorMessages;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.service.CharactersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/characters")
public class CharactersController {

    @Autowired
    CharactersServiceInterface charactersService;


    @GetMapping
    ResponseEntity<List<ListCharactersDTO>> getAllCharacters (){
        return new ResponseEntity<>(charactersService.allCharacters(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CharactersDTO> getById (@Valid @PathVariable @NotNull(message = ErrorMessages.NOT_NULL) Long id) throws NotFound {
        return new ResponseEntity<>(charactersService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CharactersDTO> newCharacter (@Valid @RequestBody PostCharactersDTO postCharactersDTO) {
        return new ResponseEntity<>(charactersService.newCharacter(postCharactersDTO), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCharacter (@Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id) throws NotFound {
            charactersService.deleteCharacter(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<CharactersDTO> editCharacter (
            @Valid @PathVariable @NotNull (message = ErrorMessages.NOT_NULL) Long id,
            @Valid @RequestBody PostCharactersDTO postCharactersDTO
    ) throws DatabaseError, NotFound {
        postCharactersDTO.setId(id);
        return new ResponseEntity<>(charactersService.editCharacter(postCharactersDTO), HttpStatus.ACCEPTED);
    }


}
