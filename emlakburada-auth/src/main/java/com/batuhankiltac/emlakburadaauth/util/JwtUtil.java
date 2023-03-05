package com.batuhankiltac.emlakburadaauth.util;

import com.batuhankiltac.emlakburadaauth.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "emlak-patika-secret-key-emlak-patika-secret-key-key-emlak-patika-secret-key";
    private static final Long EXPIRATION_TIME = 300000L;
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserName(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDate(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return this.getExpirationDate(token).before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", user.getUserType().toString());
        claims.put("email", user.getEmail());
        claims.put("id", user.getId());
        claims.put("Batuhan", "KILTAÇ");
        claims.put("service", "emlakburada-auth");

        long now = System.currentTimeMillis();

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setIssuer("emlakburada")
                .setExpiration( new Date(now + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}