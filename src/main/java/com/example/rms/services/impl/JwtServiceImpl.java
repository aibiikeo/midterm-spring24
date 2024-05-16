package com.example.rms.services.impl;

import com.example.rms.entities.AccessToken;
import com.example.rms.entities.RefreshToken;
import com.example.rms.repositories.AccessTokenRepository;
import com.example.rms.repositories.RefreshTokenRepository;
import com.example.rms.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(System.currentTimeMillis() + 1000 * 60);
        String accessToken = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
        AccessToken token = new AccessToken();
        token.setAccessToken(accessToken);
        token.setAccessTokenIssuedAt(now);
        token.setAccessTokenExpiration(expiryDate);
        accessTokenRepository.save(token);

        return accessToken;
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        String refreshToken = Jwts.builder()
                .setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();

        RefreshToken token = new RefreshToken();
        token.setRefreshToken(refreshToken);
        token.setRefreshTokenIssuedAt(now);
        token.setRefreshTokenExpiration(expiryDate);
        refreshTokenRepository.save(token);

        return refreshToken;
    }


    public String extractUserName(String token) {
        return extractClaim (token, Claims::getSubject);
    }

    private <T> T extractClaim (String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims (token);
        return claimsResolvers.apply(claims);
    }

    private Key getSigninKey(){
        byte[] key = Decoders.BASE64.decode("7d0685c571a712d2004318414bf1747d79ebae04a0548eec79d0a65e8aac3cd7");
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims (String token) {
        return Jwts.parserBuilder().setSigningKey (getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

}
