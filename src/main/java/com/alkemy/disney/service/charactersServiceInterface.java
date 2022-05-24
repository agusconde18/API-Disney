package com.alkemy.disney.service;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.DeleteCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;

public interface charactersServiceInterface {

    CharactersDTO newCharacter (PostCharactersDTO newChara );

    void deleteCharacter ( DeleteCharactersDTO deleteChar );

    CharactersDTO editCharacter ( PostCharactersDTO editCharacter );

}
