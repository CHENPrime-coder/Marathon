package com.example.marathon.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class TokenService {
    // Tokens expire after 24 hours
    private static final long EXPIRE_MILLIS = 24 * 60 * 60 * 1000L;
    private static final String DEFAULT_SECRET = "ChangeMeToASecureSecretKeyForJwtToken123";
    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes());
    }

    public String generateToken(String email) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + EXPIRE_MILLIS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Optional<String> parseEmail(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.ofNullable(claims.getSubject());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
