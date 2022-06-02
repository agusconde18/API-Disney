package com.alkemy.disney.auth.service;

import com.alkemy.disney.auth.dto.RoleDTO;
import com.alkemy.disney.auth.dto.UserDTO;
import com.alkemy.disney.auth.entity.UserDat;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface AuthService {
    UserDTO saveUser (UserDTO user);
    RoleDTO saveRole (RoleDTO role);
    UserDTO addRoleToUser(String username, String roleName);
    Optional<UserDTO> findByToken(String token);
    List<UserDTO> getAllUsers();
}
