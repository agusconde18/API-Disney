package com.alkemy.disney.service;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.DeleteCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;

import java.util.List;

public interface CharactersServiceInterface {

    CharactersDTO newCharacter (PostCharactersDTO newChara );

    void deleteCharacter ( Long deleteChar );

    CharactersDTO editCharacter ( PostCharactersDTO editCharacter );

    public List<CharactersDTO> allCharacters ();

    CharactersDTO getById( long id );

}
