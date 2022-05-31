package com.alkemy.disney.service.impl;


import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.ListCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.exception.ErrorMessages;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterDatRepository;
import com.alkemy.disney.service.CharactersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharactersService implements CharactersServiceInterface {

    CharacterMapper characterMapper = CharacterMapper.INSTANCE;

    @Autowired
    CharacterDatRepository characterDatRepository;

    @Override
    public List<ListCharactersDTO> allCharacters (){
        return characterDatRepository.findAll()
                .stream().map( characterMapper::charactersToDTOList )
                .collect(Collectors.toList());
    }

    @Override
    public CharactersDTO getById(long id) throws NotFound {
        if(characterDatRepository.existsById(id))
            return characterMapper.charactersToDTO(characterDatRepository.getById(id));
        throw new NotFound(ErrorMessages.CHARACTER_NOT_FOUND);
    }

    @Override
    public CharactersDTO newCharacter(PostCharactersDTO newChara) {

            CharacterDat newCharacter = characterMapper.PostCharactersDToCharacterDat(newChara);
            characterDatRepository.save(newCharacter);
            return characterMapper.charactersToDTO(newCharacter);

    }

    @Override
    public void deleteCharacter(Long delcharId) throws NotFound {
        if(characterDatRepository.existsById(delcharId)) {
            characterDatRepository.deleteById(delcharId);
            return ;
        }
        throw new NotFound(ErrorMessages.CHARACTER_NOT_FOUND);
    }

    @Override
    public CharactersDTO editCharacter(PostCharactersDTO editCharacter) throws NotFound {
        if(characterDatRepository.existsById(editCharacter.getId())) {
            CharacterDat characterDat = characterMapper.PostCharactersDToCharacterDat(editCharacter);
            characterDatRepository.save(characterDat);
            return characterMapper.charactersToDTO(characterDat);
        }

        throw new NotFound(ErrorMessages.CHARACTER_NOT_FOUND);
    }
}
