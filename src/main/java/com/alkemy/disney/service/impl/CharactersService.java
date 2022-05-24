package com.alkemy.disney.service.impl;


import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.DeleteCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.entity.CharacterDat;
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
    public List<CharactersDTO> allCharacters (){
        return characterDatRepository.findAll()
                .stream().map( characterMapper::charactersToDTO )
                .collect(Collectors.toList());
    }

    @Override
    public CharactersDTO newCharacter(PostCharactersDTO newChara) {
        CharacterDat newCharacter = characterMapper.PostCharactersDToCharacterDat(newChara);
        characterDatRepository.save(newCharacter);
        return characterMapper.charactersToDTO(newCharacter);
    }

    @Override
    public void deleteCharacter(DeleteCharactersDTO deleteChar) {
        CharacterDat newCharacter = characterMapper.DTOToCharacter(deleteChar);
        characterDatRepository.deleteById(newCharacter.getId());
    }

    @Override
    public CharactersDTO editCharacter(PostCharactersDTO editCharacter) {
        CharacterDat characterDat = characterMapper.PostCharactersDToCharacterDat(editCharacter);
        if(characterDatRepository.findById(characterDat.getId()).isEmpty())
            return null;
        characterDatRepository.save(characterDat);
        return characterMapper.charactersToDTO(characterDat);
    }
}
