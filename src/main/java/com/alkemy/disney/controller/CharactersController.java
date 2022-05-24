package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("characters")
public class CharactersController {



    @GetMapping("/")
    List<CharactersDTO> getAllCharacters (){

        return null;
    }
}
