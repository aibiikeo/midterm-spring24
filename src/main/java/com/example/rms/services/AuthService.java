package com.example.rms.services;

import com.example.rms.dto.JwtAuthResponse;
import com.example.rms.dto.RefreshTokenRequest;
import com.example.rms.dto.SignUpRequest;
import com.example.rms.dto.SigninRequest;
import com.example.rms.entities.User;

public interface AuthService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthResponse signin(SigninRequest signinRequest);
    JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
