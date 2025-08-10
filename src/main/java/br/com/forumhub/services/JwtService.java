package br.com.forumhub.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expirationMillis;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration}") long expirationMillis) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMillis = expirationMillis;
    }

    public String gerarToken(UserDetails userDetails) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .claims()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(exp)
                .and()
                .signWith(key) // chave simétrica
                .compact();
    }

    public boolean validarToken(String token, UserDetails userDetails) {
        String username = extrairUsername(token);
        return username.equals(userDetails.getUsername()) && !estaExpirado(token);
    }

    public String extrairUsername(String token) {
        return extrairClaims(token).getSubject();
    }

    public boolean estaExpirado(String token) {
        Date expiration = extrairClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims extrairClaims(String token) {
        return Jwts.parser()
                .verifyWith(key) // nova forma de verificar assinatura
                .build()
                .parseSignedClaims(token)
                .getPayload(); // substitui getBody()
    }
}
