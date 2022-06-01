package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.ListCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.entity.CharacterDat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterMapper INSTANCE = Mappers.getMapper(CharacterMapper.class);

    CharactersDTO CharactersToDTO(CharacterDat category);

    CharacterDat DTOToCharacter ( CharactersDTO characterDTO);

    CharacterDat PostCharactersDToCharacterDat ( PostCharactersDTO characterPostDTO);

    ListCharactersDTO CharactersToDTOList(CharacterDat category);

}
