package com.example.demo.config.security;

import com.example.demo.domain.entities.TokensEntity;
import com.example.demo.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenExpirationInMiliSec}")
    private Long tokenExpiration;
    @Value("${jwt.refreshTokenExpirationInMiliSec}")
    private Long refreshTokenExpiration;
    private final TokenService tokenService;

    public JwtUtil(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String generateToken(String username,String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenExpiration);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        String token = Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        saveNewToken(userId,token);
        return token;
    }

    private void saveNewToken(String userId, String token) {
        TokensEntity tokensEntity = TokensEntity.builder()
                .accessToken(token)
                .userId(userId)
                .build();
        tokenService.addToken(tokensEntity);
    }

    public String generateRefreshToken(String token,String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpiration);
        Map<String, Object> claims = new HashMap<>();
        claims.put("accessToken", token);
        claims.put("username", username);
        return Jwts.builder()
                .setSubject(token)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
        return claims.get("userId").toString();
    }

    public String getTokenFromRefreshToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(refreshToken).getBody();
        return claims.get("accessToken").toString();
    }

    public String getUsernameFromRefreshToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(refreshToken).getBody();
        return claims.get("username").toString();
    }


    public Boolean isTokenExpired(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
        final Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public Long getTokenExpiry() {
        return tokenExpiration;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
            return tokenService.isThisTokenExist(token);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateRefreshTokenToken(String refreshToken) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(refreshToken);
            String token = getTokenFromRefreshToken(refreshToken);
            return tokenService.isThisTokenExist(token);
        } catch (Exception e) {
            return false;
        }
    }

    public void updateAccessToken(String oldToken,String newToken){
        String id = getUserIdFromToken(oldToken);
        TokensEntity tokensEntity = TokensEntity.builder()
                .userId(id)
                .accessToken(newToken).build();
        tokenService.updateToken(oldToken,tokensEntity);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
