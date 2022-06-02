package com.alkemy.disney.auth.controller;


import com.alkemy.disney.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/login")
    ResponseEntity<?> loginController (){
        try {
            return new ResponseEntity<>("", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    ResponseEntity<?> users(){
        return new ResponseEntity<>(authService.getAllUsers(), HttpStatus.OK);
    }


}
