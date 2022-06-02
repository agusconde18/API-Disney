package com.alkemy.disney.auth.service.impl;

import com.alkemy.disney.auth.Mapper.RoleMapper;
import com.alkemy.disney.auth.Mapper.UserMapper;
import com.alkemy.disney.auth.dto.RoleDTO;
import com.alkemy.disney.auth.dto.UserDTO;
import com.alkemy.disney.auth.entity.Autorities;
import com.alkemy.disney.auth.entity.ERole;
import com.alkemy.disney.auth.entity.UserDat;
import com.alkemy.disney.auth.repository.AutoritiesRepository;
import com.alkemy.disney.auth.repository.UserRepository;

import com.alkemy.disney.auth.service.AuthService;
import com.alkemy.disney.exception.NotFound;
import com.alkemy.disney.exception.NotValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl  implements AuthService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AutoritiesRepository autoritiesRepository;

    UserMapper userMapper = UserMapper.INSTANCE;
    RoleMapper roleMapper = RoleMapper.INSTANCE;

   // @Override
    public String login(String username, String password) {
        Optional<UserDat> customer = userRepository.findByUsernameAndPassword(username,password);
        if(customer.isPresent()){
            String token = UUID.randomUUID().toString();
            UserDat custom= customer.get();
            custom.setToken(token);
            userRepository.save(custom);
            return token;
        }

        return "";
    }

    @Override
    public UserDTO saveUser(UserDTO user) {
        UserDat usuario = userMapper.DTOToUser(user);
        if(userRepository.findByUsername(usuario.getUsername()).isPresent())
            throw new NotValid("El usuario ya existe.");
        if(usuario.getRoles().stream()
                .anyMatch(rol -> autoritiesRepository.findByRole(rol.getRole()) == null)
        )
            throw new NotValid("Algun/os roles no existen");

        userRepository.save(usuario);

        return userMapper.UserToDTO(usuario);
    }

    @Override
    public RoleDTO saveRole(RoleDTO role) {
        Autorities rol = roleMapper.RoleDTOToAutorities(role);

        if(autoritiesRepository.existsByRole(rol.getRole()))
            throw new NotFound("Rol no encontrado, primero debe crearse");

        autoritiesRepository.save(rol);

        return roleMapper.AutoritiesToRoleDTO(rol);
    }

    @Override
    public UserDTO addRoleToUser(String username, String roleName) {
        Autorities rol = autoritiesRepository.findByRole(roleName);
        if(rol == null)
            throw new NotFound("Rol no encontrado, primero debe crearse");
        Optional<UserDat> userDetails = userRepository.findByUsername(username);
        if(userDetails.isEmpty())
            throw new NotFound("Usuario inexistente");
        if(userDetails.get().getRoles().contains(rol))
            throw new NotValid("El rol ya pertenece al usuario");

        userDetails.get().getRoles().add(rol);
        userRepository.save(userDetails.get());

        return userMapper.UserToDTO(userDetails.get());
    }

    @Override
    public Optional<UserDTO> findByToken(String token) {
        Optional<UserDat> customer= userRepository.findByToken(token);
        if(customer.isPresent()){
            UserDat user = customer.get();
            return Optional.of(userMapper.UserToDTO(user));
        }
        return  Optional.empty();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapper.UserToDTO(userRepository.findAll());
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserDat> userDat = userRepository.findByUsername(userName);
        if(userDat.isEmpty())
            return null;
        UserDat usuario = userDat.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usuario.getRoles()
                .forEach( rol -> authorities
                        .add(
                                new SimpleGrantedAuthority(rol.getRole())
                        )
                );

        return new User(usuario.getUsername(), usuario.getPassword(),authorities);
    }
}
