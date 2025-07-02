package org.example.emplyeemanagment.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtHelper {
    @Value("${app.jwt-secret}")
    private String JWT_SECRET;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        final Date tokenExpirationDate = extractClaim(token, Claims::getExpiration);

        boolean usernameMatch = Objects.equals(username, userDetails.getUsername());
        boolean tokenIsExpired = tokenExpirationDate.before(new Date(System.currentTimeMillis()));

        return usernameMatch && !tokenIsExpired;
    }

    //   Crée un token JWT pour un utilisateur sans claims personnalisés. si le token est valide

    public String generateToken(UserDetails userDetails) {
        return this.generateToken(new HashMap<>(), userDetails);
    }


    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claim1 = extractAllClaims(token);
        return claimResolver.apply(claim1);
    }


    public Claims extractAllClaims(String token) {
        return
                Jwts.parserBuilder()
                        .setSigningKey(getSigninKey())
                        .build().
                         parseClaimsJws(token)
                        .getBody();


    }

    public SecretKey getSigninKey() {
        byte[] bytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }


    /*
    [ Requête Login ]
       ↓
    [ UsernamePasswordAuthenticationToken ]
           ↓
    [ AuthenticationManager ]
           ↓
    → passe à →
    [ DaoAuthenticationProvider ]
           ↓
    → utilise →
    [ UserDetailsService ]
           ↓
    → récupère →
    [ UserDetails (de la base) ]
           ↓
    → compare les mots de passe avec →
    [ PasswordEncoder ]
           ↓
    ✔ Si ok → Authentifié
    ✖ Sinon → Exception

            */


}