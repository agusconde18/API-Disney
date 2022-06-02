package com.alkemy.disney.auth.dto;

import com.alkemy.disney.auth.entity.Autorities;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class UserDTO {

    String email;

    String username;

    String password;

    String token;

    Set<RoleDTO> roles;

}
