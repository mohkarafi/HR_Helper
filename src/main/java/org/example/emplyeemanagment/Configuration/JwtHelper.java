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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtHelper {
    @Value("${app.jwt-secret}")
    private String JWT_SECRET;


    /*Extrait le nom d'utilisateur (subject) depuis le token JWT.  si le token contient "sub": "mohamed.karafi" avec cette methode il retourne mohamed karafi */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*  on verfie si L'utilisateur dans le token correspond au UserDetails & Le token n'est pas expiré  */

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

    public <T>T extractClaim(String token , Function<Claims , T> claimResolver) {
        Claims claim1 = extractAllClaims(token);
        return claimResolver.apply(claim1);
    }

    /*But : Cette méthode décodifie un token JWT, vérifie sa signature avec une clé secrète, puis extrait toutes les informations contenues dans la partie payload du token, appelées les claims.
     */
    public Claims extractAllClaims(String token) {
        return
                Jwts.parserBuilder()
                        .setSigningKey(getSigninKey())
                        .build().
                        parseClaimsJws(token)
                        .getBody();

        /*
        Jwts.parserBuilder()	Crée un constructeur d’un parseur JWT (outil pour lire un JWT).
        .setSigningKey(getSigninKey())	Définit la clé secrète utilisée pour vérifier que le token est authentique (non modifié).
       .build()	Construit le parseur JWT final.
       .parseClaimsJws(token)	Analyse le token JWT en vérifiant la signature et le format.
        .getBody()	Récupère le contenu payload, c’est-à-dire les claims.*/
    }

    public SecretKey getSigninKey() {
        byte[] bytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }





}