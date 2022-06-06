package com.alkemy.disney.auth.service;

import com.alkemy.disney.auth.payload.request.LoginRequest;
import com.alkemy.disney.auth.payload.request.SignupRequest;
import com.alkemy.disney.auth.payload.response.JwtResponse;
import com.alkemy.disney.auth.payload.response.MessageResponse;

public interface AuthService {

    JwtResponse signIn ( LoginRequest loginRequest );

    MessageResponse register ( SignupRequest signUpRequest );
}
