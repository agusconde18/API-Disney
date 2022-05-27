package com.alkemy.disney.auth.service.impl;

import com.alkemy.disney.auth.entity.UserDat;
import com.alkemy.disney.auth.repository.UserRepository;
import com.alkemy.disney.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class AuthServiceImpl implements AuthService {


    @Autowired
    UserRepository userRepository;

    @Override
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
    public Optional<UserDat> findByToken(String token) {
        Optional<UserDat> customer= userRepository.findByToken(token);
        if(customer.isPresent()){
            UserDat user = customer.get();
            return Optional.of(user);
        }
        return  Optional.empty();
    }


}
