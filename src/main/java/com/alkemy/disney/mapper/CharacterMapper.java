package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.Characters.CharactersDTO;
import com.alkemy.disney.dto.Characters.DeleteCharactersDTO;
import com.alkemy.disney.dto.Characters.PostCharactersDTO;
import com.alkemy.disney.entity.CharacterDat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterMapper INSTANCE = Mappers.getMapper(CharacterMapper.class);

    CharactersDTO charactersToDTO(CharacterDat category);

    CharacterDat DTOToCharacter ( CharactersDTO characterDTO);

    CharacterDat DTOToCharacter ( PostCharactersDTO characterPostDTO);

    CharacterDat DTOToCharacter (DeleteCharactersDTO charactersDTO );

}
