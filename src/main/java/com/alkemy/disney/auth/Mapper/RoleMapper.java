package com.alkemy.disney.auth.Mapper;

import com.alkemy.disney.auth.dto.RoleDTO;
import com.alkemy.disney.auth.dto.UserDTO;
import com.alkemy.disney.auth.entity.Autorities;
import com.alkemy.disney.auth.entity.UserDat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Autorities RoleDTOToAutorities (RoleDTO roleDTO);

    RoleDTO AutoritiesToRoleDTO (Autorities autorities);

}
