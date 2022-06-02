package com.alkemy.disney.auth.Mapper;

import com.alkemy.disney.auth.dto.RoleDTO;
import com.alkemy.disney.auth.dto.UserDTO;
import com.alkemy.disney.auth.entity.Autorities;
import com.alkemy.disney.auth.entity.UserDat;
import com.alkemy.disney.mapper.CharacterMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDat DTOToUser (UserDTO userDTO);

    UserDTO UserToDTO (UserDat userDat);

    List<UserDTO> UserToDTO (List<UserDat> userDat);
}
