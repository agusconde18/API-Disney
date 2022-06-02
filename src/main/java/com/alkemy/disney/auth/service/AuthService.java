package com.alkemy.disney.auth.service;

import com.alkemy.disney.auth.entity.UserDat;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface AuthService {
    UserDat saveUser (UserDat user);
    Role saveRole (Role role);
    void addRoleToUser(String username, String roleName);
    Optional<UserDat> findByToken(String token);
    List<UserDat> getAllUsers();
}
