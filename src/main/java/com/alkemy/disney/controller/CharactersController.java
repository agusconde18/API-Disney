package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.DeleteCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.service.CharactersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/characters")
public class CharactersController {

    @Autowired
    CharactersServiceInterface charactersService;


    @GetMapping("/")
    List<CharactersDTO> getAllCharacters (){
        return charactersService.allCharacters();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    CharactersDTO newCharacter (@RequestBody PostCharactersDTO postCharactersDTO) {
        return charactersService.newCharacter(postCharactersDTO);
    }

    @DeleteMapping("/")
    String deleteCharacter (@RequestBody DeleteCharactersDTO delCharacters) {
        charactersService.deleteCharacter(delCharacters);
        return "oK";
    }

    @PostMapping("/edit")
    CharactersDTO editCharacter (@RequestBody PostCharactersDTO postCharactersDTO){
        return charactersService.editCharacter(postCharactersDTO);
    }


}
