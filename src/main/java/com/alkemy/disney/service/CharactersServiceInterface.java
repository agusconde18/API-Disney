package com.alkemy.disney.service;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.ListCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.exception.ServiceError;

import java.util.List;

public interface CharactersServiceInterface {

    CharactersDTO newCharacter (PostCharactersDTO newChara ) throws ServiceError;

    void deleteCharacter ( Long deleteChar ) throws DatabaseError;

    CharactersDTO editCharacter ( PostCharactersDTO editCharacter ) throws DatabaseError;

    public List<ListCharactersDTO> allCharacters ();

    CharactersDTO getById( long id ) throws DatabaseError;

}
