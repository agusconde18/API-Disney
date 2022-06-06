package com.alkemy.disney.auth.controller;

import com.alkemy.disney.auth.entity.EnumRole;
import com.alkemy.disney.auth.entity.Role;
import com.alkemy.disney.auth.entity.User;
import com.alkemy.disney.auth.payload.request.LoginRequest;
import com.alkemy.disney.auth.payload.request.SignupRequest;
import com.alkemy.disney.auth.payload.response.JwtResponse;
import com.alkemy.disney.auth.payload.response.MessageResponse;
import com.alkemy.disney.auth.repository.RoleRepo;
import com.alkemy.disney.auth.repository.UserRepo;
import com.alkemy.disney.auth.security.JwTUtils;
import com.alkemy.disney.auth.service.AuthService;
import com.alkemy.disney.auth.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.signIn(loginRequest));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(authService.register(signUpRequest));

    }
}
