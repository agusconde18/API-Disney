package com.alkemy.disney.service.impl;


import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.ListCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.entity.CharacterDat;
import com.alkemy.disney.entity.Film;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
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
    public CharactersDTO getById(long id) throws DatabaseError {
        if(characterDatRepository.existsById(id))
        return characterMapper.charactersToDTO(characterDatRepository.getById(id));
        else
            throw new DatabaseError("No se pudo encontrar un personaje con ese id");
    }

    @Override
    public CharactersDTO newCharacter(PostCharactersDTO newChara) throws ServiceError {
        if(newChara.getAge()!=null&&newChara.getName()!=null&&newChara.getStory()!=null) {
            CharacterDat newCharacter = characterMapper.PostCharactersDToCharacterDat(newChara);
            characterDatRepository.save(newCharacter);
            return characterMapper.charactersToDTO(newCharacter);
        }
        throw new ServiceError("Los campos deben ser no nulos");
    }

    @Override
    public void deleteCharacter(Long delcharId) throws DatabaseError {
        if(characterDatRepository.existsById(delcharId)) {
            CharacterDat characterDat = characterDatRepository.getById(delcharId);
            for (Film film : characterDat.getActFilm()) {
                film.getCharacters().remove(characterDat);
            }
            characterDatRepository.deleteById(delcharId);
        }else
            throw new DatabaseError("No se pudo encontrar un personaje con ese id");
    }

    @Override
    public CharactersDTO editCharacter(PostCharactersDTO editCharacter) throws DatabaseError {
        if(characterDatRepository.existsById(editCharacter.getId())) {
            CharacterDat characterDat = characterMapper.PostCharactersDToCharacterDat(editCharacter);
            characterDatRepository.save(characterDat);
            return characterMapper.charactersToDTO(characterDat);
        }
        else
            throw new DatabaseError("No se pudo encontrar un personaje con ese id");
    }
}
