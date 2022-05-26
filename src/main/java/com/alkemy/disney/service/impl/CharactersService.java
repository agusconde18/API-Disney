package com.alkemy.disney.service.impl;


import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.ListCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Film;
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
    public CharactersDTO getById(long id) {
        return characterMapper.charactersToDTO(characterDatRepository.getById(id));
    }

    @Override
    public CharactersDTO newCharacter(PostCharactersDTO newChara) {
        CharacterDat newCharacter = characterMapper.PostCharactersDToCharacterDat(newChara);
        characterDatRepository.save(newCharacter);
        return characterMapper.charactersToDTO(newCharacter);
    }

    @Override
    public void deleteCharacter(Long delcharId) {
        CharacterDat characterDat = characterDatRepository.getById(delcharId);
        for(Film film : characterDat.getActFilm()){
            film.getCharacters().remove(characterDat);
        }
        characterDatRepository.deleteById(delcharId);
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
