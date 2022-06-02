package com.alkemy.disney.auth.service;

import com.alkemy.disney.auth.dto.RoleDTO;
import com.alkemy.disney.auth.entity.UserDat;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface AuthService {
    UserDat saveUser (UserDat user);
    RoleDTO saveRole (RoleDTO role);
    UserDat addRoleToUser(String username, String roleName);
    Optional<UserDat> findByToken(String token);
    List<UserDat> getAllUsers();
}
