package com.example.rms.controllers;

import com.example.rms.dto.JwtAuthResponse;
import com.example.rms.dto.RefreshTokenRequest;
import com.example.rms.dto.SignUpRequest;
import com.example.rms.dto.SigninRequest;
import com.example.rms.entities.AccessToken;
import com.example.rms.entities.User;
import com.example.rms.repositories.AccessTokenRepository;
import com.example.rms.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    private final AuthService authService;

    @PostMapping("api/v1/auth/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signup(signUpRequest));
    }

    @PostMapping("api/v1/auth/signin")
    public ResponseEntity<JwtAuthResponse> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authService.signin(signinRequest));
    }

    @PostMapping("api/v1/auth/refresh")
    public ResponseEntity<JwtAuthResponse> refresh (@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("api/v1/customer")
    public ResponseEntity<String> customer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Optional<AccessToken> token = accessTokenRepository.findTopByOrderByIdDesc();
        if (token.isPresent()) {
            AccessToken accessToken = token.get();
            Date expirationDate = accessToken.getAccessTokenExpiration();
            return ResponseEntity.ok("Hi customer " + currentUserName + ". Your access token expires on: " + expirationDate.toString());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve access token.");
        }
    }

    @GetMapping("api/v1/admin")
    public ResponseEntity<String> admin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Optional<AccessToken> token = accessTokenRepository.findTopByOrderByIdDesc();
        if (token.isPresent()) {
            AccessToken accessToken = token.get();
            Date expirationDate = accessToken.getAccessTokenExpiration();
            return ResponseEntity.ok("Hi Admin " + currentUserName + ". Your access token expires on: " + expirationDate.toString());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve access token.");
        }
    }
}
