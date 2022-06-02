package com.alkemy.disney.auth.service.impl;

import com.alkemy.disney.auth.entity.ERole;
import com.alkemy.disney.auth.entity.UserDat;
import com.alkemy.disney.auth.repository.AutoritiesRepository;
import com.alkemy.disney.auth.repository.UserRepository;

import com.alkemy.disney.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl  implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AutoritiesRepository autoritiesRepository;

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
    public UserDat saveUser(UserDat user) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public Optional<UserDat> findByToken(String token) {
        Optional<UserDat> customer= userRepository.findByToken(token);
        if(customer.isPresent()){
            UserDat user = customer.get();
            return Optional.of(user);
        }
        return  Optional.empty();
    }

    @Override
    public List<UserDat> getAllUsers() {
        return userRepository.findAll();
    }


}
