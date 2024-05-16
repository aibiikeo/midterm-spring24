package com.example.rms.services.impl;

import com.example.rms.dto.JwtAuthResponse;
import com.example.rms.dto.RefreshTokenRequest;
import com.example.rms.dto.SignUpRequest;
import com.example.rms.dto.SigninRequest;
import com.example.rms.entities.User;
import com.example.rms.repositories.UserRepository;
import com.example.rms.services.AuthService;
import com.example.rms.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User signup (SignUpRequest signUpRequest) {
        User user = new User();

        user.setUsername(signUpRequest.getUsername());
        user.setRole(signUpRequest.getRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }


    public JwtAuthResponse signin (SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),
                signinRequest.getPassword()));

        var user = userRepository.findByUsername(signinRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or "));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);


        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setRefreshToken(refreshToken);
        return jwtAuthResponse;
    }


    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName (refreshTokenRequest.getRefreshToken());
        User user = userRepository.findByUsername(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)){
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

            jwtAuthResponse.setToken(jwt);
            jwtAuthResponse.setRefreshToken(refreshToken);
            return jwtAuthResponse;
        }
        return null;
    }
}
